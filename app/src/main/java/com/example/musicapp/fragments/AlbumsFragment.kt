package com.example.musicapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicapp.R
import com.example.musicapp.adapters.AlbumsAdapter
import com.example.musicapp.databinding.FragmentAlbumsBinding
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.network.factory.AlbumsViewModelFactory
import com.example.musicapp.network.models.AlbumsViewModel
import com.example.musicapp.repository.AlbumsRepository
import com.example.musicapp.utils.Resource


class AlbumsFragment : Fragment() {

    private lateinit var binding: FragmentAlbumsBinding
    private lateinit var albumsViewModel: AlbumsViewModel
    private lateinit var adapter: AlbumsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAlbumsBinding.inflate(layoutInflater, container, false)

        val genreName = arguments?.getString("genre_name")

        albumsViewModel = ViewModelProvider(
            this,
            AlbumsViewModelFactory(
                AlbumsRepository()
            )
        )[AlbumsViewModel::class.java]

        // get the albums
        getAlbumsByName(genreName!!)

        albumsViewModel.albums.observe(requireActivity()) { response ->
            handleAlbumsResponse(response)
        }


        return binding.root
    }

    private fun getAlbumsByName(name: String) {
        albumsViewModel.getAlbumsList(name)
    }


    private fun handleAlbumsResponse(response: Resource<Albums>?) {
        when (response) {
            is Resource.Loading -> {
                // TODO handle loading
                Log.d("api_loading_albums", "Loading 🚀")
            }
            is Resource.Error -> {
                // TODO handle error
                Log.d("api_error", response.message.toString())
            }
            is Resource.Success -> {
                if (response.data != null) {
                    adapter = AlbumsAdapter(requireActivity(), response.data.albums.album)

                    binding.rvAlbums.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvAlbums.adapter = adapter

                    adapter.setOnClickListener(object : AlbumsAdapter.OnClickListener {
                        override fun onClick(position: Int) {
                            TODO("Not yet implemented")
                        }
                    })
                }
            }
            else -> {

            }
        }
    }
}