package com.example.examtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecycleViewAdapter(private val locationList: List<Locations>):
    RecyclerView.Adapter<RecycleViewAdapter.RecyclerViewHolder>() {
    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val informationTextView: TextView = itemView.findViewById(R.id.information)
        private val locationTextView: TextView = itemView.findViewById(R.id.locationText)
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bindLocation(location: Locations){
            locationTextView.text = location.location
            informationTextView.text = location.information

            Glide.with(itemView)
                .load(location.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView)

    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pictures, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindLocation(locationList[position])
    }

    override fun getItemCount() = locationList.size
    }
