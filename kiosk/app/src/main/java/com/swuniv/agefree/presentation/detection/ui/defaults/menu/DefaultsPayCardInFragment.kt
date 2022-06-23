package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentDefaultOrderListBinding
import com.swuniv.agefree.databinding.FragmentDefaultPayCardInBinding
import com.swuniv.agefree.presentation.detection.utils.toWon
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
        val menu = arguments?.getSerializable("menu") as Menu?
        if (menu != null) {
            Log.d("TAG", "onViewCreated: " + menu.price.toWon())
            binding.inputCardTextView.text = menu.price.toWon()
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            if (menu != null) {
                val sendBundle = bundleOf("menu" to menu)
                requireView().findNavController().navigate(R.id.defaultsPayCardOutFragment, sendBundle)
            }else {
                requireView().findNavController().navigate(R.id.defaultsPayCardOutFragment)
            }
        }
    }
}