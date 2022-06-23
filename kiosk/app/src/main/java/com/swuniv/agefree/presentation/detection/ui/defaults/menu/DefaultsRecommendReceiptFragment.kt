package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentDefaultReceiptBinding
import com.swuniv.agefree.databinding.FragmentDefaultRecommendReceiptBinding
import com.swuniv.agefree.presentation.detection.utils.toWon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DefaultsRecommendReceiptFragment : Fragment() {

    private var _binding: FragmentDefaultRecommendReceiptBinding? = null
    private val binding get() = _binding!!
    private lateinit var selectedMenu: Menu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefaultRecommendReceiptBinding.inflate(inflater, container, false)
        selectedMenu = arguments?.getSerializable("menu") as Menu
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            requireView().findNavController().navigate(R.id.currentFragment_defaultMenuFragment)
        }

        with(binding) {
            Glide.with(requireContext()).load(selectedMenu.image).into(firstMenuImageView)
            firstPriceTextView.text = selectedMenu.price.toWon()
            firstMenuPrice.text = selectedMenu.price.toWon()
            totalMenuPrice.text = selectedMenu.price.toWon()
            firstMenuTextView.text = selectedMenu.name
            firstMenuName.text = selectedMenu.name
        }

//        binding.firstMenuImageView.setImageResource(R.drawable.coffee_02)
//        binding.secondMenuImageView.setImageResource(R.drawable.cake_03)
    }
}