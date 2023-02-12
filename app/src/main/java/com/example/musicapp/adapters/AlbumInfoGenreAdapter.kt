package com.example.musicapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.GenreItemBinding
import com.example.musicapp.models.album_info.Tags
import com.example.musicapp.models.genres.Tag

class AlbumInfoGenreAdapter(
    private val context: Context,
    private val tag: Tags,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mListener: OnClickListener? = null
    var limit = 10;
    val list = tag.tag

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent,
            false
        ), mListener!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if (holder is MyViewHolder) {
            holder.binding.tvGenreText.text = model.name.uppercase()
        }
    }

    override fun getItemCount(): Int {
        return if (limit == 10)
            10
        else
            list.size
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.mListener = listener
    }

    class MyViewHolder(val binding: GenreItemBinding, listener: OnClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onClick(adapterPosition)
            }

        }
    }
}