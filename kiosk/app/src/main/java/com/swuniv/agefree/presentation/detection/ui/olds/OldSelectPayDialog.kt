package com.swuniv.agefree.presentation.detection.ui.olds

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.swuniv.agefree.R
import kotlin.math.roundToInt


class OldSelectPayDialog : DialogFragment() {

    private lateinit var selectedPayType: MutableLiveData<PayType>
    private lateinit var onCreditDialogDismissListener: OnCreditDialogDismissListener

    fun setDismissListener(onCreditDialogDismissListener: OnCreditDialogDismissListener) {
        this.onCreditDialogDismissListener = onCreditDialogDismissListener
    }

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

        (view.findViewById<Button>(R.id.pay_button)).setOnClickListener {
            onCreditDialogDismissListener.onDismiss(selectedPayType.value!!)
            dismissAllowingStateLoss()
        }


        selectedPayType.observe(viewLifecycleOwner) {
            clearChecked() // 선택 클리어

            // 클릭 타입에 따라 배경 색상 변경
            when (it) {
                PayType.CREDIT_CARD -> {
                    checkView(R.id.credit_card_button, R.id.credit_card_text_view)
                }
                PayType.CREDIT -> {
                    checkView(R.id.credit_button, R.id.credit_text_view)
                }
                PayType.SAMSUNG_PAY -> {
                    checkView(R.id.samsung_pay_button, R.id.samsung_pay_text)
                }
                PayType.KAKAO_PAY -> {
                    checkView(R.id.kakao_pay_button, R.id.kakao_pay_text)
                }
                else -> {
                    clearChecked()
                }
            }

            (view.findViewById<Button>(R.id.pay_button)).isEnabled = true
            (view.findViewById<Button>(R.id.pay_button)).isClickable = true

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
        val textViewIdList = listOf<Int>(
            R.id.credit_card_text_view,
            R.id.credit_text_view,
            R.id.samsung_pay_text,
            R.id.kakao_pay_text
        )

        cardViewIdList.forEach { id ->
            view?.findViewById<CardView>(id)?.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        textViewIdList.forEach { id ->
            view?.findViewById<TextView>(id)?.setTextColor(Color.parseColor("#4E4E4E"))

        }
    }

    private fun checkView(cardViewId: Int, textViewId: Int) {
        view?.findViewById<CardView>(cardViewId)?.setCardBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.normalPurple
            )
        )

        view?.findViewById<TextView>(textViewId)?.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
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