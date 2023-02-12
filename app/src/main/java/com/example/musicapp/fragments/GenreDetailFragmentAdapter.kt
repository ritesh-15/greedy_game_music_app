package com.example.musicapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class GenreDetailFragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val genreName: String,
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val totalTabs = 3;

    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val fragment = AlbumsFragment()
                val bundle = Bundle()
                bundle.putString("genre_name", genreName)
                fragment.arguments = bundle
                fragment
            }
            1 -> {
                val fragment = ArtistsFragment()
                val bundle = Bundle()
                bundle.putString("genre_name", genreName)
                fragment.arguments = bundle
                fragment
            }
            2 -> {
                val fragment = TracksFragment()
                val bundle = Bundle()
                bundle.putString("genre_name", genreName)
                fragment.arguments = bundle
                fragment
            }
            else -> {
                val fragment = AlbumsFragment()
                val bundle = Bundle()
                bundle.putString("genre_name", genreName)
                fragment.arguments = bundle
                fragment
            }
        }
    }
}