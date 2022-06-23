package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.swuniv.agefree.R
import com.swuniv.agefree.presentation.detection.utils.toWon
import kotlin.math.roundToInt


class DefaultsDefaultsSelectPayDialog(var selectedMenu: Menu? = null) : DialogFragment() {

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

        view.findViewById<MaterialCardView>(R.id.credit_card_button)
            .setOnClickListener {
                setSelected(it)
                view.findViewById<MaterialCardView>(R.id.credit_card_button).setCardBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.normalPurple
                    )
                )
                view.findViewById<TextView>(R.id.credit_card_text_view).setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                view.findViewById<ImageView>(R.id.credit_card_icon).setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                payBtn.isEnabled = true
            }

        payBtn.setOnClickListener {
            dismissAllowingStateLoss()
            if (selectedMenu != null) {
                Log.d("hello", "onViewCreated: "+selectedMenu!!.price.toWon())
                val bundle = bundleOf("menu" to selectedMenu)
                findNavController().navigate(R.id.defaultsPayCardInFragment, bundle)
            }else {
                findNavController().navigate(R.id.defaultsPayCardInFragment)
            }
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