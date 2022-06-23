package com.swuniv.agefree.presentation.detection.ui.olds

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.swuniv.agefree.BuildConfig
import com.swuniv.agefree.R
import com.swuniv.agefree.presentation.detection.data.network.RetrofitBuilder
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.BestMenuResponse
import com.swuniv.agefree.presentation.detection.utils.PreferenceManager
import com.swuniv.agefree.presentation.detection.utils.toWon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt


class MenuRecommendDialog : DialogFragment() {

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
        //val menuService = RetrofitBuilder.menuApi.getBestMenu(age, gender!!)
        val menuService = RetrofitBuilder.menuApi.getBestMenu(10, "female")
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
                    val menu = (response.body() as BestMenuResponse).bestMenuContent.menu
                    val price = 5000

                    view.findViewById<TextView>(R.id.title).text = recommendMsg
                    view.findViewById<TextView>(R.id.menu_name).text = menu
                    view.findViewById<TextView>(R.id.menu_price).text = price.toWon()

                    when (menu) {
                        "추천메뉴" -> Glide.with(requireContext()).load(R.drawable.cake_02)
                            .into(view.findViewById<ImageView>(R.id.menu_img))
                        "아메리카노" -> Glide.with(requireContext()).load(R.drawable.coffee_03)
                            .into(view.findViewById<ImageView>(R.id.menu_img))
                        "캐모마일" -> Glide.with(requireContext()).load(R.drawable.tea_06)
                            .into(view.findViewById<ImageView>(R.id.menu_img))
                        "티라미수케이크" -> Glide.with(requireContext()).load(R.drawable.cake_03)
                            .into(view.findViewById<ImageView>(R.id.menu_img))
                        "복숭아아이스티" -> Glide.with(requireContext()).load(R.drawable.tea_03)
                            .into(view.findViewById<ImageView>(R.id.menu_img))
                    }
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
            dismissAllowingStateLoss()
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