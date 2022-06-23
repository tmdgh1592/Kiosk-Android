package com.swuniv.agefree.presentation.detection.ui.defaults.menu.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.*

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CoffeeFragment()
            1 -> AidFragment()
            2 -> JuiceFragment()
            3 -> TeaFragment()
            4 -> CakeFragment()
            else -> CoffeeFragment()
        }
    }
}