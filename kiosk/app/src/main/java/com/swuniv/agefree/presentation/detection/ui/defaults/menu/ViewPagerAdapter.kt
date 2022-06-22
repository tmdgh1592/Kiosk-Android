package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MenuFragment()
            1 -> MenuFragment()
            2 -> MenuFragment()
            3 -> MenuFragment()
            4 -> MenuFragment()
            else -> MenuFragment()
        }
    }
}