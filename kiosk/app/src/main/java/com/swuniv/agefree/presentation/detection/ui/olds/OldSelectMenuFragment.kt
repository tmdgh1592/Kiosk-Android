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
import com.swuniv.agefree.presentation.detection.utils.DataProvider
import com.swuniv.agefree.presentation.detection.utils.HorizontalItemDecorator
import com.swuniv.agefree.presentation.detection.utils.VerticalItemDecorator

class OldSelectMenuFragment : Fragment() {

    private var _binding: FragmentOldSelectMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuAdapter: OldMenuAdapter
    var data = mutableListOf<Menu>()

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
        val recommendDialog = MenuRecommendDialog()
        recommendDialog.setDismissListener(object : OnRecommendDialogDismissListener {
            override fun onDismissWithBuying(menu: Menu) {
                val bundle = bundleOf("menu" to menu)
                findNavController().navigate(R.id.action_oldSelectMenuFragment_to_oldOrderListFragment3, bundle)
            }
        })

        recommendDialog.show(parentFragmentManager, "recommend")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

        binding.topContainer.changeUiBtn.setOnClickListener {
            //context!!.showToast("클릭")
            requireView().findNavController()
                .navigate(R.id.action_oldSelectMenuFragment_to_defaultMenuFragment)
        }

        with(binding) {
            // 메뉴 카테고리 선택 리스너 (커피, 탄산음료, etc...)
            categoryRadioGroup.isCheckedButtonId.observe(viewLifecycleOwner) {
                when (it) {
                    R.id.radio_coffee_btn -> {
                        data = DataProvider.getCoffeeList().toMutableList()
                    }
                    R.id.radio_soda_btn -> {
                        data = DataProvider.getAdeList().toMutableList()
                    }
                    R.id.radio_tea_btn -> {
                        data = DataProvider.getTeaList().toMutableList()
                    }
                    R.id.radio_juice_btn -> {
                        data = DataProvider.getJuiceList().toMutableList()
                    }
                    R.id.radio_cake_btn -> {
                        data = DataProvider.getCakeList().toMutableList()
                    }
                }

                menuAdapter.apply {
                    menuList = data
                    notifyDataSetChanged()
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

                // 다음에 메뉴 고를 때를 대비해서 기본값인 커피(Coffee)로 재변경
                binding.categoryRadioGroup.setSelectedRadioButton(binding.radioCoffeeBtn)
            }
        })

        binding.menuRecyclerView.adapter = menuAdapter
        binding.menuRecyclerView.apply {
            addItemDecoration(HorizontalItemDecorator(8))
            addItemDecoration(VerticalItemDecorator(30))
        }
        //binding.menuRecyclerView.layoutManager = GridLayoutManager(context, 2)

        data = DataProvider.getCoffeeList().toMutableList()
        menuAdapter.apply {
            menuList = data
            notifyDataSetChanged()
        }
    }

}