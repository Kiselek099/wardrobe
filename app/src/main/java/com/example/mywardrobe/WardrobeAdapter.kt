package com.example.mywardrobe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WardrobeAdapter(private val items: List<WardrobeItem>) :
    RecyclerView.Adapter<WardrobeAdapter.WardrobeViewHolder>() {

    private var onItemClickListener:OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(wardrobeItem: WardrobeItem,position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WardrobeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wardrobe, parent, false)
        return WardrobeViewHolder(view)
    }

    override fun onBindViewHolder(holder: WardrobeViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        holder.imageView.setImageResource(item.imageResId)
        holder.itemView.setOnClickListener{
            if(onItemClickListener!=null){
                onItemClickListener!!.onItemClick(item ,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class WardrobeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener=onItemClickListener
    }
}