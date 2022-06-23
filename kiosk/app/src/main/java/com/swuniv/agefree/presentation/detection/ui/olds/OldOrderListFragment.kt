package com.swuniv.agefree.presentation.detection.ui.olds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentOldOrderListBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.utils.convertOldAmount
import com.swuniv.agefree.presentation.detection.utils.convertOldColdHot
import com.swuniv.agefree.presentation.detection.utils.convertOldSoftDeep
import com.swuniv.agefree.presentation.detection.utils.toWon

class OldOrderListFragment : Fragment(), OnCreditDialogDismissListener {

    private var _binding: FragmentOldOrderListBinding? = null
    private val binding get() = _binding!!
    lateinit var selectedMenu: Menu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOldOrderListBinding.inflate(inflater, container, false)
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
                menuAndCountTextView.text = "$name ${orderCount}개"
                totalPayTextView.text = price.toWon()
                Glide.with(requireContext()).load(image).into(menuImageView)

                coldHotTextView.text = option1.convertOldColdHot()
                softDeepTextView.text = option2.convertOldSoftDeep()
                amountTextView.text = option3.convertOldAmount()
            }


            orderBtn.setOnClickListener {
                val oldSelectPayDialog = OldSelectPayDialog()
                oldSelectPayDialog.setDismissListener(this@OldOrderListFragment)
                oldSelectPayDialog.show(parentFragmentManager, "DialogOldSelectPay")
            }

            backButton.setOnClickListener {
                requireView().findNavController().popBackStack()
            }

            homeButton.setOnClickListener {
                requireView().findNavController()
                    .navigate(R.id.currentFragment_oldSelectMenuFragment)
            }

        }
    }


    // 결제하기 종류 선택 완료시 다이얼로그 종료
    // 다음화면으로 전환
    override fun onDismiss(creditType: PayType) {
        val bundle = bundleOf("menu" to selectedMenu)
        requireView().findNavController().navigate(R.id.action_oldOrderListFragment_to_oldCardInFragment, bundle)
    }
}
