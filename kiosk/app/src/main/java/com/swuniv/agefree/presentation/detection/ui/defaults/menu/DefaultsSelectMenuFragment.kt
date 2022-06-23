package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentDefaultSelectMenuBinding
import com.swuniv.agefree.presentation.detection.ui.olds.OldSelectPayDialog
import com.swuniv.agefree.presentation.detection.utils.convertOldAmount
import com.swuniv.agefree.presentation.detection.utils.convertOldColdHot
import com.swuniv.agefree.presentation.detection.utils.convertOldSoftDeep
import com.swuniv.agefree.presentation.detection.utils.toWon

class DefaultsSelectMenuFragment : Fragment() {

    private var _binding: FragmentDefaultSelectMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var selectedMenu: Menu

    private val temperature: String = "cold".convertOldColdHot()
    private val shot: String = "normal".convertOldSoftDeep()
    private val size: String = "normal".convertOldAmount()

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
        initState()

        with(binding) {
            // 선택한 메뉴 정보 표시
            selectedMenu.let {
                menuTextView.text = it.name
                priceTextView.text = it.price.toWon()
                Glide.with(requireContext()).load(it.image).into(menuImageView)
            }
            /**
             * 옵션 셀렉하면 다른 뷰 셀렉 상태 해제
             * */
            // ice/hot 부분
            iceButton.setOnClickListener {
                setSelected(it)
                cancelSelected(hotButton)
                selectedMenu.option1 = "cold".convertOldColdHot()
            }
            hotButton.setOnClickListener {
                setSelected(it)
                cancelSelected(iceButton)
                selectedMenu.option1 = "hot".convertOldColdHot()
            }

            // shot 부분
            softButton.setOnClickListener {
                setSelected(it)
                cancelSelected(normalButton)
                cancelSelected(deepButton)
                selectedMenu.option2 = "soft".convertOldSoftDeep()
            }
            normalButton.setOnClickListener {
                setSelected(it)
                cancelSelected(softButton)
                cancelSelected(deepButton)
                selectedMenu.option2 = "normal".convertOldSoftDeep()
            }
            deepButton.setOnClickListener {
                setSelected(it)
                cancelSelected(softButton)
                cancelSelected(normalButton)
                selectedMenu.option2 = "deep".convertOldSoftDeep()
            }

            // shot 부분
            regularButton.setOnClickListener {
                setSelected(it)
                cancelSelected(mediumButton)
                cancelSelected(largeButton)
                selectedMenu.option3 = "normal".convertOldAmount()
            }
            mediumButton.setOnClickListener {
                setSelected(it)
                cancelSelected(regularButton)
                cancelSelected(largeButton)
                selectedMenu.option3 = "many".convertOldAmount()
            }
            largeButton.setOnClickListener {
                setSelected(it)
                cancelSelected(regularButton)
                cancelSelected(mediumButton)
                selectedMenu.option3 = "moremoremoremore".convertOldAmount()
            }

            confirmButton.setOnClickListener {
                selectedMenu.apply {
                    option1 = temperature
                    option2 = shot
                    option3 = size
                }
                val bundle = bundleOf("option" to selectedMenu)
                DefaultsMenuRecommendDialog().show(parentFragmentManager, "DefaultsMenuRecommendDialog")
//                requireView().findNavController().navigate(R.id.)
            }
        }
    }

    private fun initState() {
        binding.iceButton.isSelected = true
        binding.normalButton.isSelected = true
        binding.regularButton.isSelected = true
    }

    private fun setSelected(view: View) {
        view.isSelected = true
    }

    private fun cancelSelected(view: View) {
        view.isSelected = false
    }
}

