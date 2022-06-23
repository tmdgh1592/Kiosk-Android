package com.swuniv.agefree.presentation.detection.ui.olds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentOldSelectCountBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.utils.convertOldAmount
import com.swuniv.agefree.presentation.detection.utils.convertOldColdHot
import com.swuniv.agefree.presentation.detection.utils.convertOldSoftDeep
import com.swuniv.agefree.presentation.detection.utils.toWon

class OldSelectCountFragment : Fragment() {

    private var _binding: FragmentOldSelectCountBinding? = null
    private val binding get() = _binding!!
    lateinit var selectedMenu: Menu
    private var count = MutableLiveData(1)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOldSelectCountBinding.inflate(inflater, container, false)
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
            selectedMenu.apply {
                menuTextView.text = name
                priceTextView.text = price.toWon()
                Glide.with(requireContext()).load(image).into(menuImageView)

                coldHotTextView.text = option1.convertOldColdHot()
                softDeepTextView.text = option2.convertOldSoftDeep()
                amountTextView.text = option3.convertOldAmount()
            }

            minusButton.setOnClickListener {
                if (count.value!! > 1) {
                    count.value = count.value!! - 1
                }
            }
            plusButton.setOnClickListener {
                if (count.value!! < 99) {
                    count.value = count.value!! + 1
                }
            }
            doneBtn.setOnClickListener {
                selectedMenu.orderCount = count.value!!
                selectedMenu.price = selectedMenu.price * count.value!!
                val bundle = bundleOf("menu" to selectedMenu)
                requireView().findNavController().navigate(R.id.oldOrderListFragment, bundle)
            }

            homeButton.setOnClickListener {
                requireView().findNavController()
                    .navigate(R.id.currentFragment_oldSelectMenuFragment)
            }

            // 숫자 변경시 화면 갱신
            count.observe(viewLifecycleOwner) {
                countTextView.text = it.toString()
            }

        }
    }
}