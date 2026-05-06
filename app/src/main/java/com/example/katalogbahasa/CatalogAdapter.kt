package com.example.katalogbahasa

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        holder.tvDesc.text = item.shortDesc

        holder.imgLogo.setImageResource(item.iconResId)
        holder.imgLogo.imageTintList = null

        holder.tvDesc.text = item.shortDesc

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra("EXTRA_TITLE", item.name)

            intent.putExtra("EXTRA_DESC", item.fullDesc)

            intent.putExtra("EXTRA_ICON", item.iconResId)

            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataList.size

    fun updateData(newData: List<Language>) {
        dataList = newData
        notifyDataSetChanged()
    }
}