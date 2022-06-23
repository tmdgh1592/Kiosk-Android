package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.swuniv.agefree.databinding.FragmentDefaultOrderListBinding

class DefaultsOrderListFragment : Fragment() {

    private var _binding: FragmentDefaultOrderListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefaultOrderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.orderButton.setOnClickListener {
            DefaultsDefaultsSelectPayDialog().show(
                parentFragmentManager,
                "DefaultsDefaultsSelectPayDialog"
            )
        }
    }

}