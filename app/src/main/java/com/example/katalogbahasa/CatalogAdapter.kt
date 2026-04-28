package com.example.katalogbahasa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView // 1. Tambahkan import ini
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatalogAdapter(private var dataList: List<Language>) :
    RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_language_name)
        val tvDesc: TextView = view.findViewById(R.id.tv_language_desc)
        val imgLogo: ImageView = view.findViewById(R.id.img_language_logo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_katalog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.tvName.text = item.name
        holder.tvDesc.text = item.description

        holder.imgLogo.setImageResource(item.iconResId)

        holder.imgLogo.imageTintList = null
    }

    override fun getItemCount() = dataList.size

    fun updateData(newData: List<Language>) {
        dataList = newData
        notifyDataSetChanged()
    }
}