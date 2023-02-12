package com.example.musicapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.musicapp.adapters.AlbumInfoGenreAdapter
import com.example.musicapp.adapters.ArtistInfoGenreAdapter
import com.example.musicapp.adapters.TopAlbumsAdapter
import com.example.musicapp.adapters.TopTracksAdapter
import com.example.musicapp.constants.Constants
import com.example.musicapp.databinding.ActivityArtistInfoBinding
import com.example.musicapp.models.album_info.Tag
import com.example.musicapp.models.album_info.Tags
import com.example.musicapp.models.artist_info.ArtistInfo
import com.example.musicapp.models.top_albums.TopAlbums
import com.example.musicapp.models.top_tracks.TopTracks
import com.example.musicapp.network.factory.AlbumsViewModelFactory
import com.example.musicapp.network.factory.ArtistsViewModelFactory
import com.example.musicapp.network.factory.TracksViewModelFactory
import com.example.musicapp.network.models.AlbumsViewModel
import com.example.musicapp.network.models.ArtistViewModel
import com.example.musicapp.network.models.TracksViewModel
import com.example.musicapp.repository.AlbumsRepository
import com.example.musicapp.repository.ArtistsRepository
import com.example.musicapp.repository.TracksRepository
import com.example.musicapp.utils.FormatNumber
import com.example.musicapp.utils.Resource

class ArtistInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistInfoBinding
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var tracksViewModel: TracksViewModel
    private lateinit var albumsViewModel: AlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val artistName = intent.getStringExtra(Constants.ARTIST_NAME)!!

        artistViewModel = ViewModelProvider(
            this,
            ArtistsViewModelFactory(
                ArtistsRepository()
            )
        )[ArtistViewModel::class.java]

        tracksViewModel = ViewModelProvider(
            this,
            TracksViewModelFactory(
                TracksRepository()
            )
        )[TracksViewModel::class.java]

        albumsViewModel = ViewModelProvider(
            this,
            AlbumsViewModelFactory(
                AlbumsRepository()
            )
        )[AlbumsViewModel::class.java]

        // get the artist details
        getArtistDetailsByName(artistName)

        // get top tracks
        getTopTracksByName(artistName)

        // get top albums
        getTopAlbumsByName(artistName)

        // observe the artist info response
        artistViewModel.artistInfo.observe(this) { response ->
            handleArtistInfoResponse(response)
        }

        tracksViewModel.topTracks.observe(this) { response ->
            handleTopTrackResponse(response)
        }

        albumsViewModel.topAlbums.observe(this) { response ->
            handleTopAlbums(response)
        }
    }

    private fun handleTopAlbums(response:  Resource<TopAlbums>) {
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
                    val adapter = TopAlbumsAdapter(this, response.data.topalbums.album)
                    binding.rvTopAlbums.adapter = adapter
                    binding.rvTopAlbums.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                }
            }
            else -> {

            }
        }
    }

    private fun getTopAlbumsByName(artistName: String) {
        return albumsViewModel.getTopAlbums(artistName)
    }

    private fun handleTopTrackResponse(response: Resource<TopTracks>?) {
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
                    val adapter = TopTracksAdapter(this, response.data.toptracks.track)
                    binding.rvTopTracks.adapter = adapter
                    binding.rvTopTracks.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                }
            }
            else -> {

            }
        }
    }

    private fun getTopTracksByName(artistName: String) {
        tracksViewModel.getTopTracks(artistName)
    }

    private fun handleArtistInfoResponse(response: Resource<ArtistInfo>?) {
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
                    val tags = response.data.artist.tags.tag
                    val adapter = ArtistInfoGenreAdapter(this, tags)
                    binding.rvGenresList.adapter = adapter
                    binding.rvGenresList.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                    adapter.limit = Int.MAX_VALUE

                    adapter.setOnClickListener(object : ArtistInfoGenreAdapter.OnClickListener {
                        override fun onClick(position: Int) {
                            // open genre details activity
                            val intent =
                                Intent(this@ArtistInfoActivity, GenreDetailActivity::class.java)
                            intent.putExtra(Constants.GENRE_NAME, tags[position].name)
                            startActivity(intent)
                        }

                    })

                    // set up other details
                    val formatter = FormatNumber()
                    binding.collapsingToolbar.title =
                        response.data.artist.name
                    binding.tvArtistDescription.text = response.data.artist.bio.content
                    binding.tvFollowersCount.text =
                        formatter.format(response.data.artist.stats.playcount.toLong())
                    binding.tvPlayCount.text =
                        formatter.format(response.data.artist.stats.listeners.toLong())
                    loadArtistImage(response.data.artist.mbid)
                }
            }
            else -> {

            }
        }
    }

    private fun loadArtistImage(id: String) {
        var imageURL = "https://picsum.photos/seed/${id}/200/300?blur=5"

        Glide
            .with(this)
            .load(imageURL)
            .centerCrop()
            .into(binding.ivBackground)
    }

    private fun getArtistDetailsByName(artistName: String) {
        artistViewModel.getArtistInfo(artistName)
    }
}