package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swuniv.agefree.R

class MenuAdapter(private val context: Context) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    var menuList = mutableListOf<Menu>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_default_menu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = itemView.findViewById(R.id.menu_name)
        private val price: TextView = itemView.findViewById(R.id.menu_price)
        private val image: ImageView = itemView.findViewById(R.id.menu_img)

        fun bind(item: Menu) {
            name.text = item.name
            price.text = item.price.toString() + "원"
            Glide.with(itemView).load(item.image).into(image)
        }
    }

}