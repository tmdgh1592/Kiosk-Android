package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.swuniv.agefree.databinding.FragmentDefaultRecommendOrderListBinding
import com.swuniv.agefree.presentation.detection.utils.toWon

class DefaultsRecommendOrderListFragment : Fragment() {

    private var _binding: FragmentDefaultRecommendOrderListBinding? = null
    private val binding get() = _binding!!
    private lateinit var selectedMenu: Menu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        selectedMenu = arguments?.getSerializable("menu") as Menu
        _binding = FragmentDefaultRecommendOrderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            orderButton.setOnClickListener {
                DefaultsDefaultsSelectPayDialog(selectedMenu).show(
                    parentFragmentManager,
                    "DefaultsDefaultsSelectPayDialog"
                )
            }

            Glide.with(requireContext()).load(selectedMenu.image).into(firstMenuImageView)
            firstMenuTextView.text = selectedMenu.name
            firstPriceTextView.text = selectedMenu.price.toWon()
            totalPayTextView.text = selectedMenu.price.toWon()
        }
    }

}