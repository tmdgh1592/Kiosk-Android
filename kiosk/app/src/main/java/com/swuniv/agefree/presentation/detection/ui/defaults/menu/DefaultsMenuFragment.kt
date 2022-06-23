package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentDefaultMenuBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.adapter.ViewPagerAdapter
import com.swuniv.agefree.presentation.detection.ui.olds.MenuRecommendDialog
import com.swuniv.agefree.presentation.detection.ui.olds.OnRecommendDialogDismissListener
import com.swuniv.agefree.presentation.detection.utils.PreferenceManager

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

        val recommendDialog = MenuRecommendDialog()
        recommendDialog.setDismissListener(object : OnRecommendDialogDismissListener {
            override fun onDismissWithBuying(menu: Menu) {
                val bundle = bundleOf("menu" to menu)
                findNavController().navigate(R.id.action_defaultMenuFragment_to_defaultsRecommendOrderListFragment, bundle)
            }
        })

        recommendDialog.show(parentFragmentManager, "recommend")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager2()

        binding.toolbar.changeUiBtn.setOnClickListener {
            requireView().findNavController().navigate(R.id.oldSelectMenuFragment)
        }

        // 남자면
        if(PreferenceManager.getString(requireContext(), PreferenceManager.genderKey) == "male") {
            Glide.with(requireContext()).load(R.drawable.banner_man_20).into(binding.bannerImageView)
        }else { // 여자면
            Glide.with(requireContext()).load(R.drawable.banner_woman_20).into(binding.bannerImageView)
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