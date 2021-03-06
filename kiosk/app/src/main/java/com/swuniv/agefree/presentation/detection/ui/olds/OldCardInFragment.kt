package com.swuniv.agefree.presentation.detection.ui.olds

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentOldPayCardInBinding
import com.swuniv.agefree.presentation.detection.data.network.RetrofitBuilder
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.MenuResponse
import com.swuniv.agefree.presentation.detection.utils.PreferenceManager
import com.swuniv.agefree.presentation.detection.utils.showToast
import com.swuniv.agefree.presentation.detection.utils.toWon
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            val title = inputCardTitleTextView.text.toString()
            val ssb = SpannableStringBuilder(title)
            ssb.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.deepPurple)),
                0,
                5,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            inputCardTitleTextView.text = ssb


            backButton.setOnClickListener {
                job.cancel()
                requireView().findNavController().popBackStack()
            }
            homeButton.setOnClickListener {
                job.cancel()
                requireView().findNavController()
                    .navigate(R.id.currentFragment_oldSelectMenuFragment)
            }

            // ??? ?????? ?????? ??????
            totalPriceTextView.text = selectedMenu.price.toWon()

            // ????????? ????????? ?????? ??? ?????? ????????? ??????
            selectedMenu.inOut =
                PreferenceManager.getString(requireContext(), PreferenceManager.inOutKey)!!
            selectedMenu.gender =
                PreferenceManager.getString(requireContext(), PreferenceManager.genderKey)!!
            selectedMenu.age =
                PreferenceManager.getInt(requireContext(), PreferenceManager.ageKey)

            val menuApi = RetrofitBuilder.menuApi.postMenu(selectedMenu)
            menuApi.enqueue(object : Callback<MenuResponse> {
                override fun onResponse(
                    call: Call<MenuResponse>,
                    response: Response<MenuResponse>
                ) {
                    if (response.isSuccessful && response.body()?.success!!) {
                        // ??????????????? ?????? ????????? ?????????????????? ????????????
                        // 5??? ?????? ?????????????????? ??????
                        CoroutineScope(Dispatchers.Default + job).launch {
                            delay(5000)
                            withContext(Dispatchers.Main) {
                                val bundle = bundleOf("menu" to selectedMenu)
                                requireView().findNavController()
                                    .navigate(
                                        R.id.action_oldCardInFragment_to_oldCardOutFragment,
                                        bundle
                                    )
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MenuResponse>, t: Throwable) {
                    // ?????? ????????? ????????????
                    requireContext().showToast("??? ??? ?????? ????????? ????????? ?????????????????????.")
                    requireView().findNavController().popBackStack()
                }
            })


        }
    }
}