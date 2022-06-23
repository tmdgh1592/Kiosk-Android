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
import com.swuniv.agefree.databinding.FragmentOldOrderListBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.utils.convertOldAmount
import com.swuniv.agefree.presentation.detection.utils.convertOldColdHot
import com.swuniv.agefree.presentation.detection.utils.convertOldSoftDeep
import com.swuniv.agefree.presentation.detection.utils.toWon

class OldOrderListFragment : Fragment() {

    private var _binding: FragmentOldOrderListBinding? = null
    private val binding get() = _binding!!
    lateinit var selectedMenu: Menu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOldOrderListBinding.inflate(inflater, container, false)
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
                menuAndCountTextView.text = "$name ${orderCount}개"
                totalPayTextView.text = price.toWon()
                Glide.with(requireContext()).load(image).into(menuImageView)

                coldHotTextView.text = option1.convertOldColdHot()
                softDeepTextView.text = option2.convertOldSoftDeep()
                amountTextView.text = option3.convertOldAmount()
            }


            orderBtn.setOnClickListener {
                val bundle = bundleOf("menu" to selectedMenu)
                OldSelectPayDialog().show(parentFragmentManager, "DialogOldSelectPay")
            }

            homeButton.setOnClickListener {
                requireView().findNavController()
                    .navigate(R.id.currentFragment_oldSelectMenuFragment)
            }

        }
    }
}

interface OnSelectPayCategory {
    fun onClick()
}