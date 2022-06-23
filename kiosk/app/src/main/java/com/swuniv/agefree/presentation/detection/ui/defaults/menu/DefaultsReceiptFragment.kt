package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentDefaultReceiptBinding
import com.swuniv.agefree.presentation.detection.ui.start.StartActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DefaultsReceiptFragment : Fragment() {

    private var _binding: FragmentDefaultReceiptBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefaultReceiptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            requireActivity().finish()
            startActivity(Intent(requireContext(), StartActivity::class.java))
            //requireView().findNavController().navigate(R.id.currentFragment_defaultMenuFragment)
        }

        binding.firstMenuImageView.setImageResource(R.drawable.coffee_02)
        binding.secondMenuImageView.setImageResource(R.drawable.cake_03)
    }
}