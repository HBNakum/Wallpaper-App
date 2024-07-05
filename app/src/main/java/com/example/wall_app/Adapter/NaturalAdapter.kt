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

class NaturalAdapter(val requireContext: Context, val listnatural: ArrayList<AbstractModel>) :RecyclerView.Adapter<NaturalAdapter.abstractViewHolder>() {

    inner class abstractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val imageView=itemView.findViewById<ImageView>(R.id.nature_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): abstractViewHolder {
        return abstractViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_nature,parent,false)
        )
    }

    override fun onBindViewHolder(holder: abstractViewHolder, position: Int) {
        holder.imageView
        Glide.with(requireContext).load(listnatural[position].link).into(holder.imageView)
        holder.imageView.setOnClickListener {
            val intent= Intent(requireContext, FinalWallpaper::class.java)
            intent.putExtra("link", listnatural[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listnatural.size
}