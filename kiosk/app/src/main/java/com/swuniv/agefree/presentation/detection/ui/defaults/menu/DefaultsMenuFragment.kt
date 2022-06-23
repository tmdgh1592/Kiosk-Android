package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentDefaultMenuBinding

class DefaultsMenuFragment : Fragment() {

    private var _binding: FragmentDefaultMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefaultMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager2()
        binding.toolbar.changeUiBtn.setOnClickListener {
            requireView().findNavController().navigate(R.id.currentFragment_oldSelectMenuFragment)
        }

    }

    private fun viewPager2() {
        binding.tab.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 탭이 선택되었을 때
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 탭이 선택되지 않은 상태로 변경 되었을 때
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 이미 선태된 탭이 다시 선택 되었을 때
            }
        })
        binding.viewpager2.adapter = ViewPagerAdapter(requireActivity())

        TabLayoutMediator(
            binding.tab,
            binding.viewpager2
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "커피"
                1 -> tab.text = "에이드"
                2 -> tab.text = "주스"
                3 -> tab.text = "티"
                4 -> tab.text = "케이크"
            }
        }.attach()
    }

}