package com.swuniv.agefree.presentation.detection.ui.menu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        Log.e("--onBindViewHolder", menuList[position].toString())
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = itemView.findViewById(R.id.menu_name)
        private val price: TextView = itemView.findViewById(R.id.menu_price)
//        private val imgProfile: ImageView = itemView.findViewById(R.id.img_rv_photo)

        fun bind(item: Menu) {
            name.text = item.name
            price.text = item.price.toString()+"원"
        }
    }

}