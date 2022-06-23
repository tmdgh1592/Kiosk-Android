package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.swuniv.agefree.R
import kotlin.math.roundToInt


class DefaultsMenuRecommendDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.dialog_extra_menu_recommend, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        isCancelable = false

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.cancel).setOnClickListener {
            dismissAllowingStateLoss()
        }

        view.findViewById<TextView>(R.id.add).setOnClickListener {
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