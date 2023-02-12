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
import com.example.musicapp.adapters.ArtistsAdapter
import com.example.musicapp.adapters.TracksAdapter
import com.example.musicapp.databinding.FragmentArtistsBinding
import com.example.musicapp.databinding.FragmentTracksBinding
import com.example.musicapp.models.tracks.Tracks
import com.example.musicapp.network.factory.ArtistsViewModelFactory
import com.example.musicapp.network.factory.TracksViewModelFactory
import com.example.musicapp.network.models.ArtistViewModel
import com.example.musicapp.network.models.TracksViewModel
import com.example.musicapp.repository.ArtistsRepository
import com.example.musicapp.repository.TracksRepository
import com.example.musicapp.utils.Resource
import retrofit2.Response


class TracksFragment : Fragment() {

    private lateinit var binding: FragmentTracksBinding
    private lateinit var tracksViewModel: TracksViewModel
    private lateinit var adapter: TracksAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTracksBinding.inflate(layoutInflater, container, false)
        val genreName = arguments?.getString("genre_name")

        tracksViewModel = ViewModelProvider(
            this,
            TracksViewModelFactory(
                TracksRepository()
            )
        )[TracksViewModel::class.java]

        // get the tracks
        getTracksByName(genreName!!)


        tracksViewModel.tracks.observe(requireActivity()) { response ->
            handleTracksResponse(response)
        }

        return binding.root
    }

    private fun handleTracksResponse(response: Resource<Tracks>) {
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
                    adapter = TracksAdapter(requireActivity(), response.data.tracks.track)

                    binding.rvTracks.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvTracks.adapter = adapter

                    adapter.setOnClickListener(object : TracksAdapter.OnClickListener {
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

    private fun getTracksByName(genreName: String) {
        tracksViewModel.getTracks(genreName)
    }


}