package com.swuniv.agefree.presentation.detection.ui.olds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentOldReceiptBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.utils.convertOldAmount
import com.swuniv.agefree.presentation.detection.utils.convertOldColdHot
import com.swuniv.agefree.presentation.detection.utils.convertOldSoftDeep
import com.swuniv.agefree.presentation.detection.utils.toWon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OldOrderReceiptFragment : Fragment() {

    private var _binding: FragmentOldReceiptBinding? = null
    private val binding get() = _binding!!
    lateinit var selectedMenu: Menu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOldReceiptBinding.inflate(inflater, container, false)
        selectedMenu = arguments?.getSerializable("menu") as Menu

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            // 이전 Fragment에서 전달받은 데이터를 View에 적용
            with(selectedMenu) {
                Glide.with(requireContext()).load(image).into(menuImageView)
                menuAndCountTextView.text = "${name} ${orderCount}개"
                coldHotTextView.text = option1.convertOldColdHot()
                softDeepTextView.text = option2.convertOldSoftDeep()
                amountTextView.text = option3.convertOldAmount()
                receiptMenuTextView.text = name
                totalPriceTextView.text = price.toWon()


                var totalMinus = 0 // 전체 비용에서 이만큼 빼야 음료 자체만의 가격이 계산 됨.

                // 옵션 메뉴 추가비용 계산
                if (option2 == "deep") {
                    option2TextView.visibility = View.VISIBLE
                    option2PriceTextView.visibility = View.VISIBLE


                    option2TextView.text = option2.convertOldSoftDeep()

                    val option2Price = orderCount * 500
                    totalMinus += option2Price
                    option2PriceTextView.text = option2Price.toWon()
                }

                if (option3 == "many") {
                    option3TextView.visibility = View.VISIBLE
                    option3PriceTextView.visibility = View.VISIBLE

                    option3TextView.text = option3.convertOldAmount()

                    val option3Price = orderCount * 500
                    totalMinus += option3Price
                    option3PriceTextView.text = (orderCount * 500).toWon()

                } else if (option3 == "very_many") {
                    option3TextView.visibility = View.VISIBLE
                    option3PriceTextView.visibility = View.VISIBLE

                    option3TextView.text = option3.convertOldAmount()

                    val option3Price = orderCount * 700
                    totalMinus += option3Price
                    option3PriceTextView.text = (orderCount * 700).toWon()

                }

                // 음료 자체만의 가격
                menuPriceTextView.text = (price - totalMinus).toWon()

            }

            CoroutineScope(Dispatchers.Main).launch {
                // 시연용으로 카드 결제가 완료되었다고 가정하고
                // 5초 뒤에 다음화면으로 이동
                delay(5000)
                requireView().findNavController()
                    .navigate(R.id.currentFragment_oldSelectMenuFragment)
            }

        }
    }
}