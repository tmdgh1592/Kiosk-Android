package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentMenuRecyclerBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.adapter.MenuAdapter
import com.swuniv.agefree.presentation.detection.utils.DataProvider
import com.swuniv.agefree.presentation.detection.utils.HorizontalItemDecorator
import com.swuniv.agefree.presentation.detection.utils.VerticalItemDecorator
import com.swuniv.agefree.presentation.detection.utils.printLog

class CoffeeFragment : Fragment() {
    private var _binding: FragmentMenuRecyclerBinding? = null
    private val binding get() = _binding!!
    val data: ArrayList<Menu> = arrayListOf()
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuRecyclerBinding.inflate(inflater, container, false)
        "++onCreateView".printLog()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuAdapter = MenuAdapter(requireActivity(), data, onItemClickListener = {
            "$it 클릭".printLog()
            val menu = data[it]
            val bundle = bundleOf("menu" to menu)
            requireView().findNavController().navigate(R.id.action_defaultMenuFragment_to_defaultsSelectFragment,bundle)
        })
        addData()
        initRecycler()

        "++onViewCreated".printLog()
    }

    private fun initRecycler() {
        binding.recycler.adapter = menuAdapter
        binding.recycler.apply {
            addItemDecoration(HorizontalItemDecorator(8))
            addItemDecoration(VerticalItemDecorator(40))
        }
        binding.recycler.layoutManager = GridLayoutManager(context, 4)
    }

    private fun addData() {
        val img = DataProvider.getCoffeeList()
        data.add(img[0])
        data.add(img[1])
        data.add(img[2])
        data.add(img[3])
        data.add(img[4])
        data.add(img[5])
        data.add(img[3])
        data.add(img[2])
        data.add(img[1])
        data.add(img[4])
    }
}