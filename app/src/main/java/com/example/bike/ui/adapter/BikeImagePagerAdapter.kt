package com.example.bike.ui.adapter

import com.example.bike.R
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BikeImagePagerAdapter(private val imageUris: List<Uri>) : RecyclerView.Adapter<BikeImagePagerAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item_view_for_slider, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val uri = imageUris[position]
        try {
            Glide.with(holder.itemView.context)
                .load(uri)
                .into(holder.imageView)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("image adapter", "onBindViewHolder: ${e.message}")
            // Handle the exception here (e.g., show a placeholder image or log the error)
        }

    }

    override fun getItemCount(): Int = imageUris.size

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

}
