package com.swuniv.agefree.presentation.detection.ui.olds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentOldPayCardInBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import kotlinx.coroutines.*

class OldCardInFragment : Fragment() {

    private var _binding: FragmentOldPayCardInBinding? = null
    private val binding get() = _binding!!
    lateinit var selectedMenu: Menu
    private val job = Job()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOldPayCardInBinding.inflate(inflater, container, false)
        selectedMenu = arguments?.getSerializable("menu") as Menu

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            backButton.setOnClickListener {
                job.cancel()
                requireView().findNavController().popBackStack()
            }
            homeButton.setOnClickListener {
                job.cancel()
                requireView().findNavController()
                    .navigate(R.id.currentFragment_oldSelectMenuFragment)
            }

            CoroutineScope(Dispatchers.Default + job).launch {
                // 시연용으로 카드 결제가 완료되었다고 가정하고
                // 5초 뒤에 다음화면으로 이동
                delay(5000)
                withContext(Dispatchers.Main) {
                    val bundle = bundleOf("menu" to selectedMenu)
                    requireView().findNavController()
                        .navigate(R.id.action_oldCardInFragment_to_oldCardOutFragment, bundle)
                }
            }

        }
    }
}