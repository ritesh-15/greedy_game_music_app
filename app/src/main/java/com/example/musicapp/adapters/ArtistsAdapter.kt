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
import com.example.musicapp.databinding.ArtistsItemBinding
import com.example.musicapp.databinding.GenreItemBinding
import com.example.musicapp.models.albums.Album
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.artists.Artist
import com.example.musicapp.models.artists.Artists
import com.example.musicapp.models.genres.Tag

class ArtistsAdapter(
    private val context: Context,
    private val list: List<Artist>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mListener: OnClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            ArtistsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            ), mListener!!
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if (holder is MyViewHolder) {
            holder.binding.tvArtistName.text = model.name

            var imageURL = "https://picsum.photos/seed/${model.mbid}/200/300?blur=5"

            // setting up random image hence there is only white image with artist
            Glide
                .with(context)
                .load(imageURL)
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?,
                    ) {
                        holder.binding.llArtistContainer.background = resource
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

    class MyViewHolder(val binding: ArtistsItemBinding, listener: OnClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onClick(adapterPosition)
            }

        }
    }
}