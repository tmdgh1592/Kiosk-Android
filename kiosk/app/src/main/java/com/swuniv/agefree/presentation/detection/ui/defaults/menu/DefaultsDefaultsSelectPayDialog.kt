package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.swuniv.agefree.R
import kotlin.math.roundToInt


class DefaultsDefaultsSelectPayDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.dialog_default_select_pay, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        isCancelable = false

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val payBtn: TextView = view.findViewById(R.id.pay_button)
        val credit_card_button: MaterialCardView = view.findViewById(R.id.credit_card_button)


        credit_card_button.findViewById<MaterialCardView>(R.id.credit_card_button)
            .setOnClickListener {
                setSelected(it)
                payBtn.isEnabled = true
            }

        payBtn.setOnClickListener {
            dismissAllowingStateLoss()
            findNavController().navigate(R.id.defaultsPayCardInFragment)
        }

//        firstContainer.setOnClickListener {
//            setSelected(it)
//            cancelSelected(secondContainer)
//            cancelSelected(thirdContainer)
//        }

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

    private fun setSelected(view: View) {
        view.isSelected = true
    }

    private fun cancelSelected(view: View) {
        view.isSelected = false
    }
}