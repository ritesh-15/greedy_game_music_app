package com.example.musicapp.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.musicapp.databinding.AlbumItemBinding
import com.example.musicapp.databinding.GenreItemBinding
import com.example.musicapp.databinding.TrackItemBinding
import com.example.musicapp.models.albums.Album
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.genres.Tag
import com.example.musicapp.models.tracks.Track

class TracksAdapter(
    private val context: Context,
    private val list: List<Track>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mListener: OnClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            TrackItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            ), mListener!!
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if (holder is MyViewHolder) {
            holder.binding.tvTrackTitle.text = model.name
            holder.binding.tvArtistName.text = model.artist.name

            var imageURL = "https://picsum.photos/seed/${model.mbid}/200/300?blur=5"

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

    interface OnClickListener {
        fun onClick(position: Int)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.mListener = listener
    }

    class MyViewHolder(val binding: TrackItemBinding, listener: OnClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }
    }
}