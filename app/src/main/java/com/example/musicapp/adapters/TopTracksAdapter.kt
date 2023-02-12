package com.example.musicapp.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.musicapp.databinding.TopTrackItemBinding
import com.example.musicapp.models.top_tracks.Track

class TopTracksAdapter(
    private val context: Context,
    private val list: List<Track>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            TopTrackItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if (holder is MyViewHolder) {
            holder.binding.tvTrackTitle.text = model.name
            holder.binding.tvArtistName.text = model.artist.name

            val imageURL = "https://picsum.photos/seed/${model.mbid}/200/300?blur=5"

            Glide
                .with(context)
                .load(imageURL)
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?,
                    ) {
                        holder.binding.lLTrackContainer.background = resource
                    }

                })
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(val binding: TopTrackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root
        }
    }
}