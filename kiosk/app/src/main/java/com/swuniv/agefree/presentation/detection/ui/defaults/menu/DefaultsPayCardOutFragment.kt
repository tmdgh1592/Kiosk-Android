package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentDefaultPayCardInBinding
import com.swuniv.agefree.databinding.FragmentDefaultPayCardOutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DefaultsPayCardOutFragment : Fragment() {

    private var _binding: FragmentDefaultPayCardOutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefaultPayCardOutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            requireView().findNavController().navigate(R.id.defaultsReceiptFragment) //TODO 내역확인
        }
    }
}