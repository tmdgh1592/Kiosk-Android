package com.swuniv.agefree.presentation.detection.ui.olds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentOldSelectColdHotBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.utils.toWon

class OldSelectSoftDeepFragment : Fragment() {

    private var _binding: FragmentOldSelectColdHotBinding? = null
    private val binding get() = _binding!!
    lateinit var selectedMenu: Menu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOldSelectColdHotBinding.inflate(inflater, container, false)
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
            selectedMenu.let {
                menuTextView.text = it.name
                priceTextView.text = it.price.toWon()
                Glide.with(requireContext()).load(it.image).into(menuImageView)
            }

            coldContainer.setOnClickListener {
                selectedMenu.option1 = "cold"
                //findNavController().navigate(R.id)
            }
            hotContainer.setOnClickListener {
                selectedMenu.option1 = "hot"
            }

        }
    }
}