package com.swuniv.agefree.presentation.detection.ui.defaults.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.ItemDefaultMenuBinding
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import java.text.DecimalFormat

class MenuAdapter(
    private val context: Context,
    private val menuList: ArrayList<Menu>,
    private val onItemClickListener: ((Int) -> Unit)
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDefaultMenuBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, onItemClickListener)
    }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuList[position], position)
    }

    inner class ViewHolder(
        private val binding: ItemDefaultMenuBinding,
        private val onItemClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Menu, position: Int) {
            val dec = DecimalFormat("#,###")

            if (position == 0) {
                binding.tag.visibility = View.VISIBLE
                binding.tag.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_best)) //TODO 리소스 best
            } else if (position == 1) {
                binding.tag.visibility = View.VISIBLE
                binding.tag.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_new)) //TODO 리소스 new
            }

            binding.root.setOnClickListener {
                onItemClickListener.invoke(position)
            }
            binding.menuName.text = item.name
            binding.menuPrice.text = "${dec.format(item.price)}원"
            Glide.with(context).load(item.image).into(binding.menuImg)
        }
    }

}