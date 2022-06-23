package com.swuniv.agefree.presentation.detection.ui.olds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentOldSelectSoftDeepBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.utils.convertOldColdHot
import com.swuniv.agefree.presentation.detection.utils.toWon

class OldSelectSoftDeepFragment : Fragment() {

    private var _binding: FragmentOldSelectSoftDeepBinding? = null
    private val binding get() = _binding!!
    lateinit var selectedMenu: Menu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOldSelectSoftDeepBinding.inflate(inflater, container, false)
        selectedMenu = arguments?.getSerializable("menu") as Menu

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            // 선택한 메뉴 정보 표시
            selectedMenu.apply {
                menuTextView.text = name
                priceTextView.text = price.toWon()
                Glide.with(requireContext()).load(image).into(menuImageView)

                coldHotTextView.text = option1.convertOldColdHot()
            }

            softContainer.setOnClickListener {
                selectedMenu.option2 = "soft"
                it.isSelected = true
                val bundle = bundleOf("menu" to selectedMenu)
                requireView().findNavController().navigate(R.id.oldSelectAmountFragment, bundle)
            }

            normalContainer.setOnClickListener {
                selectedMenu.option2 = "normal"
                it.isSelected = true
                val bundle = bundleOf("menu" to selectedMenu)
                requireView().findNavController().navigate(R.id.oldSelectAmountFragment, bundle)
            }

            deepContainer.setOnClickListener {
                selectedMenu.option2 = "deep"
                selectedMenu.price += 500
                it.isSelected = true
                val bundle = bundleOf("menu" to selectedMenu)
                requireView().findNavController().navigate(R.id.oldSelectAmountFragment, bundle)
            }

            backButton.setOnClickListener {
                requireView().findNavController().popBackStack()
            }
            homeButton.setOnClickListener {
                requireView().findNavController()
                    .navigate(R.id.currentFragment_oldSelectMenuFragment)
            }
        }
    }
}