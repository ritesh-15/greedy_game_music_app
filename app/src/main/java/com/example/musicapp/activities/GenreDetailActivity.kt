package com.example.musicapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.constants.Constants
import com.example.musicapp.databinding.ActivityGenreDetailBinding
import com.example.musicapp.fragments.GenreDetailFragmentAdapter
import com.example.musicapp.models.genre_details.GenreDetails
import com.example.musicapp.network.factory.AlbumsViewModelFactory
import com.example.musicapp.network.factory.GenresViewModelFactory
import com.example.musicapp.network.models.AlbumsViewModel
import com.example.musicapp.network.models.GenresViewModel
import com.example.musicapp.repository.AlbumsRepository
import com.example.musicapp.repository.GenresRepository
import com.example.musicapp.utils.Resource
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class GenreDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenreDetailBinding
    private lateinit var genresViewModel: GenresViewModel
    private lateinit var albumsViewModel: AlbumsViewModel

    private val TABS = arrayOf("Albums", "Artists", "Tracks")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val genreName = intent.getStringExtra(Constants.GENRE_NAME)!!

        // initialize the view model
        genresViewModel = ViewModelProvider(
            this,
            GenresViewModelFactory(
                GenresRepository()
            )
        )[GenresViewModel::class.java]

        // navigation button click
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // call the genre details function
        getGenreDetailsByName(genreName)

        val viewPager = binding.vpTabs
        val fragmentAdapter =
            GenreDetailFragmentAdapter(supportFragmentManager, lifecycle, genreName)
        viewPager.adapter = fragmentAdapter

        TabLayoutMediator(binding.tlGenres, viewPager) { tab, position ->
            tab.text = TABS[position]
        }.attach()
    }

    // get genre details by name of genre
    private fun getGenreDetailsByName(name: String) {
        genresViewModel.getGenreDetails(name)
        genresViewModel.genreDetails.observe(this) { response ->
            handleGenreDetailsResponse(response)
        }
    }

    private fun handleGenreDetailsResponse(response: Resource<GenreDetails>?) {
        when (response) {
            is Resource.Success -> {
                if (response.data != null) {
                    // setting up the name and description of genre
                    binding.collapsingToolbar.title = response.data.tag.name.replaceFirstChar {
                        it.uppercase()
                    }
                    binding.tvDescription.text = response.data.tag.wiki.summary
                }
            }
            is Resource.Loading -> {
                // TODO handle loading
                Log.d("api_loading", "Loading 🚀")
            }
            is Resource.Error -> {
                // TODO handle error
                Log.d("api_error", response.message.toString())
            }
            else -> {

            }
        }
    }
}