package com.example.wall_app.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wall_app.FinalWallpaper
import com.example.wall_app.Model.AbstractModel
import com.example.wall_app.R

class AbstractAdapter(val requireContext: Context, val listAbstract: ArrayList<AbstractModel>) :RecyclerView.Adapter<AbstractAdapter.abstractViewHolder>() {

    inner class abstractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val imageView=itemView.findViewById<ImageView>(R.id.abstract_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): abstractViewHolder {
        return abstractViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_abstract,parent,false)
        )
    }

    override fun onBindViewHolder(holder: abstractViewHolder, position: Int) {
        holder.imageView
        Glide.with(requireContext).load(listAbstract[position].link).into(holder.imageView)
        holder.imageView.setOnClickListener {
            val intent= Intent(requireContext,FinalWallpaper::class.java)
            intent.putExtra("link", listAbstract[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listAbstract.size
}