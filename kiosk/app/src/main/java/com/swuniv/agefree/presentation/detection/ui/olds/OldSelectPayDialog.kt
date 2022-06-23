package com.swuniv.agefree.presentation.detection.ui.olds

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.swuniv.agefree.R
import kotlin.math.roundToInt


class OldSelectPayDialog : DialogFragment() {

    private lateinit var selectedPayType: MutableLiveData<PayType>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.dialog_old_select_pay, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        isCancelable = false
        selectedPayType = MutableLiveData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view.findViewById<CardView>(R.id.credit_card_button)).setOnClickListener {
            selectedPayType.value = PayType.CREDIT_CARD
        }

        (view.findViewById<CardView>(R.id.credit_button)).setOnClickListener {
            selectedPayType.value = PayType.CREDIT
        }

        (view.findViewById<CardView>(R.id.samsung_pay_button)).setOnClickListener {
            selectedPayType.value = PayType.SAMSUNG_PAY
        }

        (view.findViewById<CardView>(R.id.kakao_pay_button)).setOnClickListener {
            selectedPayType.value = PayType.KAKAO_PAY
        }


        selectedPayType.observe(viewLifecycleOwner) {
            clearChecked()

            when (it) {
                PayType.CREDIT_CARD -> {
                    checkCard(R.id.credit_card_button)
                }
                PayType.CREDIT -> {
                    checkCard(R.id.credit_button)
                }
                PayType.SAMSUNG_PAY -> {
                    checkCard(R.id.samsung_pay_button)
                }
                PayType.KAKAO_PAY -> {
                    checkCard(R.id.kakao_pay_button)
                }
                else -> {
                    clearChecked()
                }
            }

        }
    }

    // 모든 카드 체크 해제
    private fun clearChecked() {
        val cardViewIdList = listOf<Int>(
            R.id.credit_card_button,
            R.id.credit_button,
            R.id.samsung_pay_button,
            R.id.kakao_pay_button
        )

        cardViewIdList.forEach { id ->
            view?.findViewById<CardView>(id)?.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
    }

    private fun checkCard(id: Int) {
        view?.findViewById<CardView>(id)?.setCardBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.normalPurple
            )
        )
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val deviceWidth: Int = displayMetrics.widthPixels
        val deviceHeight: Int = displayMetrics.heightPixels
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = (deviceWidth * 0.9).roundToInt()
        params.height = (deviceHeight * 0.9).roundToInt()
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }
}

enum class PayType {
    CREDIT_CARD,
    CREDIT,
    SAMSUNG_PAY,
    KAKAO_PAY
}