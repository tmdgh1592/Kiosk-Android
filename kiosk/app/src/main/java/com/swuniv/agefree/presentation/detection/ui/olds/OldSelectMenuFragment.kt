package com.swuniv.agefree.presentation.detection.ui.olds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentOldSelectMenuBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.ui.olds.adapter.OldMenuAdapter
import com.swuniv.agefree.presentation.detection.ui.olds.adapter.OnMenuClickListener
import com.swuniv.agefree.presentation.detection.utils.HorizontalItemDecorator
import com.swuniv.agefree.presentation.detection.utils.VerticalItemDecorator

class OldSelectMenuFragment : Fragment() {

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
        MenuRecommendDialog().show(parentFragmentManager, "recommend")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        binding.defaultScreenButton.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_oldSelectMenuFragment_to_defaultMenuFragment)
        }

        with(binding) {
            // 메뉴 카테고리 선택 리스너 (커피, 탄산음료, etc...)
            categoryRadioGroup.isCheckedButtonId.observe(viewLifecycleOwner) {
                when (it) {
                    R.id.radio_coffee_btn -> {

                    }
                    R.id.radio_soda_btn -> {

                    }
                    R.id.radio_tea_btn -> {

                    }
                    R.id.radio_juice_btn -> {

                    }
                    R.id.radio_cake_btn -> {

                    }
                }
            }
        }

    }


    private fun initRecycler() {
        menuAdapter = OldMenuAdapter(requireContext(), object : OnMenuClickListener {
            override fun onClick(menu: Menu) {
                // 리사이클러뷰의 메뉴(음식) 클릭 리스너
                val bundle = bundleOf("menu" to menu)
                findNavController().navigate(R.id.oldSelectColdHotFragment, bundle)
            }
        })

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