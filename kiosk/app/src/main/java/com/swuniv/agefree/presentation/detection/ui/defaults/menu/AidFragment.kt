package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.FragmentMenuRecyclerBinding
import com.swuniv.agefree.presentation.detection.utils.HorizontalItemDecorator
import com.swuniv.agefree.presentation.detection.utils.VerticalItemDecorator
import com.swuniv.agefree.presentation.detection.utils.printLog

class AidFragment : Fragment() {
    private var _binding: FragmentMenuRecyclerBinding? = null
    private val binding get() = _binding!!
    val data: ArrayList<Menu> = arrayListOf()
    private lateinit var menuAdapter:MenuAdapter

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

    private fun addData(){
        for (i in 0 until 20) {
            data.add(Menu("아메리카노$i", 5000 + i, R.drawable.coffeetest))
        }
    }
}