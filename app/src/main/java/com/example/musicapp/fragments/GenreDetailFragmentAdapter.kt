package com.example.musicapp.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GenreDetailFragmentAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private val totalTabs = 3;

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AlbumsFragment()
            1 -> ArtistsFragment()
            2 -> TracksFragment()
            else -> AlbumsFragment()
        }
    }
}