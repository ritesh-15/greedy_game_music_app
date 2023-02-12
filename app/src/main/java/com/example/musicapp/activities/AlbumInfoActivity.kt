package com.example.musicapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.musicapp.adapters.AlbumInfoGenreAdapter
import com.example.musicapp.constants.Constants
import com.example.musicapp.databinding.ActivityAlbumInfoBinding
import com.example.musicapp.models.album_info.AlbumInfo
import com.example.musicapp.models.album_info.Image
import com.example.musicapp.network.factory.AlbumsViewModelFactory
import com.example.musicapp.network.models.AlbumsViewModel
import com.example.musicapp.repository.AlbumsRepository
import com.example.musicapp.utils.Resource

class AlbumInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumInfoBinding
    private lateinit var albumsViewModel: AlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumName = intent.getStringExtra(Constants.ALBUM_NAME)!!
        val artistName = intent.getStringExtra(Constants.ARTIST_NAME)!!

        albumsViewModel = ViewModelProvider(
            this,
            AlbumsViewModelFactory(
                AlbumsRepository()
            )
        )[AlbumsViewModel::class.java]

        // get album details by artist name and album name
        getAlbumDetails(artistName, albumName)

        // observe the album info response
        albumsViewModel.albumInfo.observe(this) { response ->
            handleAlbunInfoResponse(response)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }

    private fun handleAlbunInfoResponse(response: Resource<AlbumInfo>?) {
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
                    val tags = response.data.album.tags.tag
                    val adapter = AlbumInfoGenreAdapter(this, response.data.album.tags)
                    adapter.limit = Int.MAX_VALUE

                    binding.rvGenresList.adapter = adapter
                    binding.rvGenresList.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

                    adapter.setOnClickListener(object : AlbumInfoGenreAdapter.OnClickListener {
                        override fun onClick(position: Int) {
                            // open genre details activity
                            val intent =
                                Intent(this@AlbumInfoActivity, GenreDetailActivity::class.java)
                            intent.putExtra(Constants.GENRE_NAME, tags[position].name)
                            startActivity(intent)
                        }

                    })

                    // set the remaning details of the albums
                    binding.tvAlbumDescription.text = response.data.album.wiki.content
                    binding.collapsingToolbar.title = response.data.album.name
                    binding.tvArtistName.text = response.data.album.artist
                    loadAlbumImage(response.data.album.image)
                }
            }
            else -> {

            }
        }
    }

    private fun loadAlbumImage(list: List<Image>) {
        var imageURL: String? = null

        list.forEach {
            if (it.size == "extralarge") {
                imageURL = it.text
            }
        }


        Glide
            .with(this)
            .load(imageURL)
            .centerCrop()
            .into(binding.ivBackground)
    }

    private fun getAlbumDetails(artistName: String, albumName: String) {
        albumsViewModel.getAlbumInfo(artistName, albumName)
    }
}