package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentDefaultOrderListBinding
import com.swuniv.agefree.databinding.FragmentDefaultPayCardInBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DefaultsPayCardInFragment : Fragment() {

    private var _binding: FragmentDefaultPayCardInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefaultPayCardInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            requireView().findNavController().navigate(R.id.defaultsPayCardOutFragment)
        }
    }
}