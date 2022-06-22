package com.swuniv.agefree.presentation.detection.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.swuniv.agefree.databinding.FragmentMenuBinding

class MenuFragment : Fragment(){

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        return
    }

    fun viewPager2(){
        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
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
//        binding.viewpager2.adapter = RankingAdapter(requireActivity())

        TabLayoutMediator(binding.tab, binding.viewpager2){tab, position ->
            when(position){
                0 ->tab.text = "종합"
                1 ->tab.text = "PER"
                2 ->tab.text = "PBR"
                3 ->tab.text = "PSR"
            }
        }.attach()
    }
}