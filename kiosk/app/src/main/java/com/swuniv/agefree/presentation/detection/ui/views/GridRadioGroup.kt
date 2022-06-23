package com.swuniv.agefree.presentation.detection.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.IdRes
import androidx.gridlayout.widget.GridLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * A GridLayout subclass that acts like a RadioGroup. Important: it only accepts RadioButton as children.
 * Inspired by https://stackoverflow.com/a/2383978/4034572
 */
class GridRadioGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : GridLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var _isCheckedButtonId = MutableLiveData<Int>(0)
    val isCheckedButtonId: LiveData<Int> = _isCheckedButtonId

    @IdRes
    var selectedRadioButtonId: Int? = null
        get() = getSelectedRadioButton()?.id
        private set

    private fun getSelectedRadioButton(): RadioButton? {
        for (index in 0 until childCount) {
            val radioButton = getChildAt(index) as RadioButton
            if (radioButton.isChecked) return radioButton
        }
        return null
    }

    override fun onClick(view: View) {
        // While this looks inefficient, it does fix a bug (2 RadioButtons could be selected at the
        // same time) when navigating back by popping-up a fragment from the backstack.
        for (index in 0 until childCount) {
            val radioButton = getChildAt(index) as RadioButton
            radioButton.isChecked = false
        }
        val radioButton = view as RadioButton
        radioButton.isChecked = true
        _isCheckedButtonId.value = view.id
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        child?.setOnClickListener(this)
    }

}