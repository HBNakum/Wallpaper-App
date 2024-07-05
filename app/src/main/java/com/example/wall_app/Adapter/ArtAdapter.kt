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

class ArtAdapter(val requireContext: Context, val listArt: ArrayList<AbstractModel>) :RecyclerView.Adapter<ArtAdapter.abstractViewHolder>() {

    inner class abstractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val imageView=itemView.findViewById<ImageView>(R.id.art_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): abstractViewHolder {
        return abstractViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_art,parent,false)
        )
    }

    override fun onBindViewHolder(holder: abstractViewHolder, position: Int) {
        holder.imageView
        Glide.with(requireContext).load(listArt[position].link).into(holder.imageView)
        holder.imageView.setOnClickListener {
            val intent= Intent(requireContext, FinalWallpaper::class.java)
            intent.putExtra("link", listArt[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listArt.size
}