package com.swuniv.agefree.presentation.detection.ui.olds

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentOldSelectMenuBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.ui.olds.adapter.OldMenuAdapter
import com.swuniv.agefree.presentation.detection.utils.HorizontalItemDecorator
import com.swuniv.agefree.presentation.detection.utils.VerticalItemDecorator

class OldSelectMenuFragment : Fragment() {

    private var selectedCategory: MutableLiveData<MenuCategory> =
        MutableLiveData(MenuCategory.COFFEE);

    private var _binding: FragmentOldSelectMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuAdapter: OldMenuAdapter
    val data = mutableListOf<Menu>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOldSelectMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        MenuRecommendDialog().show(parentFragmentManager, "recommend")

        with(binding) {
            Log.d("TAG", "onViewCreated: " + categoryRadioGroup.selectedRadioButtonId)

            selectedCategory.observe(viewLifecycleOwner) {
                when (it) {
                    MenuCategory.COFFEE -> {

                    }
                    MenuCategory.SODA -> {

                    }
                    MenuCategory.TEA -> {

                    }
                    MenuCategory.JUICE -> {

                    }
                    MenuCategory.CAKE -> {

                    }
                }
            }
        }

    }


    private fun initRecycler() {
        menuAdapter = OldMenuAdapter(requireContext())
        binding.menuRecyclerView.adapter = menuAdapter
        binding.menuRecyclerView.apply {
            addItemDecoration(HorizontalItemDecorator(8))
            addItemDecoration(VerticalItemDecorator(30))
        }
        //binding.menuRecyclerView.layoutManager = GridLayoutManager(context, 2)

        for (i in 0 until 20) {
            data.add(Menu("아메리카노$i", 5000 + i, R.drawable.coffeetest))
        }
        menuAdapter.menuList = data

        menuAdapter.apply {
            menuList = data
            notifyDataSetChanged()
        }
    }

}