package com.example.musicapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicapp.R
import com.example.musicapp.activities.ArtistInfoActivity
import com.example.musicapp.adapters.AlbumsAdapter
import com.example.musicapp.adapters.ArtistsAdapter
import com.example.musicapp.constants.Constants
import com.example.musicapp.databinding.FragmentAlbumsBinding
import com.example.musicapp.databinding.FragmentArtistsBinding
import com.example.musicapp.models.artists.Artists
import com.example.musicapp.network.factory.AlbumsViewModelFactory
import com.example.musicapp.network.factory.ArtistsViewModelFactory
import com.example.musicapp.network.models.AlbumsViewModel
import com.example.musicapp.network.models.ArtistViewModel
import com.example.musicapp.repository.AlbumsRepository
import com.example.musicapp.repository.ArtistsRepository
import com.example.musicapp.utils.Resource


class ArtistsFragment : Fragment() {

    private lateinit var binding: FragmentArtistsBinding
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentArtistsBinding.inflate(layoutInflater, container, false)
        val genreName = arguments?.getString("genre_name")

        artistViewModel = ViewModelProvider(
            this,
            ArtistsViewModelFactory(
                ArtistsRepository()
            )
        )[ArtistViewModel::class.java]

        // get the albums
        getArtistsByName(genreName!!)

        artistViewModel.artists.observe(requireActivity()) { response ->
            handleArtistsResponse(response)
        }



        return binding.root
    }

    private fun handleArtistsResponse(response: Resource<Artists>?) {
        when (response) {
            is Resource.Loading -> {
                // TODO handle loading
                Log.d("api_loading_artists", "Loading 🚀")
            }
            is Resource.Error -> {
                // TODO handle error
                Log.d("api_error", response.message.toString())
            }
            is Resource.Success -> {
                if (response.data != null) {
                    val artists = response.data.topartists.artist
                    adapter = ArtistsAdapter(requireActivity(), artists)

                    binding.rvAlbums.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvAlbums.adapter = adapter

                    adapter.setOnClickListener(object : ArtistsAdapter.OnClickListener {
                        override fun onClick(position: Int) {
                            // go to the artist details activity
                            val intent = Intent(requireActivity(), ArtistInfoActivity::class.java)
                            intent.putExtra(Constants.ARTIST_NAME, artists[position].name)
                            startActivity(intent)
                        }
                    })
                }
            }
            else -> {

            }
        }
    }

    private fun getArtistsByName(genreName: String) {
        artistViewModel.getArtistsList(genreName)
    }

}