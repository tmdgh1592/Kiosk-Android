package com.swuniv.agefree.presentation.detection.ui.olds

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.swuniv.agefree.BuildConfig
import com.swuniv.agefree.R
import com.swuniv.agefree.presentation.detection.data.network.RetrofitBuilder
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.BestMenuResponse
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.utils.PreferenceManager
import com.swuniv.agefree.presentation.detection.utils.toWon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt


class MenuRecommendDialog : DialogFragment() {
    private lateinit var onRecommendDialogDismissListener: OnRecommendDialogDismissListener
    private var selectedMenu: Menu? = null

    fun setDismissListener(onRecommendDialogDismissListener: OnRecommendDialogDismissListener) {
        this.onRecommendDialogDismissListener = onRecommendDialogDismissListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.dialog_best_menu_recommend, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        isCancelable = false

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val age = PreferenceManager.getInt(requireContext(), PreferenceManager.ageKey)
        val gender = PreferenceManager.getString(requireContext(), PreferenceManager.genderKey)
        Log.d("user Age and gender", "onViewCreated: " + age + " " + gender!!)
        val menuService = RetrofitBuilder.menuApi.getBestMenu(age, gender)
        //val menuService = RetrofitBuilder.menuApi.getBestMenu(60, "female")

        menuService.enqueue(object : Callback<BestMenuResponse> {
            override fun onResponse(
                call: Call<BestMenuResponse>,
                response: Response<BestMenuResponse>
            ) {
                if (BuildConfig.DEBUG) {
                    Log.d("TAG", "onResponse: " + response.body().toString())
                }
                if (response.isSuccessful) {
                    val recommendMsg = (response.body() as BestMenuResponse).bestMenuContent.message
                    var menu = (response.body() as BestMenuResponse).bestMenuContent.menu
                    val price = 5000
                    val option1 = (response.body() as BestMenuResponse).bestMenuContent.option1
                    var imgResId: Int = R.drawable.coffee_03

                    view.findViewById<TextView>(R.id.title).text = recommendMsg
                    view.findViewById<TextView>(R.id.menu_name).text = menu
                    view.findViewById<TextView>(R.id.menu_price).text = price.toWon()

                    when (menu) {
                        "자몽에이드" -> imgResId = R.drawable.ade_04
                        "아메리카노" -> imgResId = R.drawable.coffee_03
                        "캐모마일" -> imgResId = R.drawable.tea_06
                        "티라미수케이크" -> imgResId = R.drawable.cake_03
                        "복숭아아이스티" -> imgResId = R.drawable.tea_03
                    }

                    // 가져온 이미지 적용
                    Glide.with(requireContext()).load(imgResId)
                        .into(view.findViewById(R.id.menu_img))


                    selectedMenu =
                        Menu(
                            name = menu,
                            price = price,
                            image = imgResId,
                            option1 = option1,
                            option2 = "normal",
                            option3 = "normal",
                            orderCount = 1,
                            inOut = "in"
                        )

                } else {
                    dismissAllowingStateLoss()
                }
            }

            override fun onFailure(call: Call<BestMenuResponse>, t: Throwable) {
                dismissAllowingStateLoss()
            }
        })

        view.findViewById<TextView>(R.id.cancel_btn).setOnClickListener {
            dismissAllowingStateLoss()
        }

        view.findViewById<TextView>(R.id.order_btn).setOnClickListener {
            if (selectedMenu != null) {
                onRecommendDialogDismissListener.onDismissWithBuying(selectedMenu!!)
                dismissAllowingStateLoss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val deviceWidth: Int = displayMetrics.widthPixels
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = (deviceWidth * 0.9).roundToInt()
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }
}