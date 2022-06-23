package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.swuniv.agefree.databinding.FragmentDefaultSelectMenuBinding


import com.swuniv.agefree.presentation.detection.utils.showToast
import com.swuniv.agefree.presentation.detection.utils.toWon

class DefaultsSelectMenuFragment : Fragment() {

    private var _binding: FragmentDefaultSelectMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var selectedMenu: Menu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefaultSelectMenuBinding.inflate(inflater, container, false)
        selectedMenu = arguments?.getSerializable("menu") as Menu

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            // 선택한 메뉴 정보 표시
            selectedMenu.let {
                menuTextView.text = it.name
                priceTextView.text = it.price.toWon()
                Glide.with(requireContext()).load(it.image).into(menuImageView)
            }
        }

        binding.iceButton.setOnClickListener {
            setSelected(it)
//            binding.iceButton.isFocused
            cancelSelected(binding.testBox)
            requireContext().showToast("클릭")
        }
        binding.testBox.setOnClickListener {
            setSelected(it)
            cancelSelected(binding.iceButton)
        }
    }
}

private fun setSelected(view: View) {
    view.isSelected = true
}

private fun cancelSelected(view: View) {
    view.isSelected = false
}