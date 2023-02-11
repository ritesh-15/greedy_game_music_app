package com.example.musicapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.adapters.GenresAdapter
import com.example.musicapp.constants.Constants
import com.example.musicapp.databinding.ActivityMainBinding
import com.example.musicapp.models.genres.Genres
import com.example.musicapp.network.factory.GenresViewModelFactory
import com.example.musicapp.network.models.GenresViewModel
import com.example.musicapp.repository.GenresRepository
import com.example.musicapp.utils.Resource
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var genresViewModel: GenresViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // disable the nested scroll of recycler view
        binding.rvGenresList.isNestedScrollingEnabled = false

        // initialize the view model
        genresViewModel = ViewModelProvider(
            this,
            GenresViewModelFactory(
                GenresRepository()
            )
        )[GenresViewModel::class.java]

        // call the genres function
        getGenresList()

        // view more tv click listener
        binding.tvViewMore.setOnClickListener {
            if (adapter.limit == Int.MAX_VALUE) {
                // unset the limit in the adapter
                adapter.limit = 10
                adapter.notifyItemChanged(10)
            } else {
                // unset the limit in the adapter
                adapter.limit = Int.MAX_VALUE
                adapter.notifyItemChanged(10)
            }
            handleTextChangeOfTV()
        }
    }

    private fun handleTextChangeOfTV() {
        if (adapter.limit == Int.MAX_VALUE) {
            binding.tvViewMore.text = "View less"
        } else {
            binding.tvViewMore.text = "View more"
        }
    }

    private fun getGenresList() {
        genresViewModel.genres.observe(this) { response ->
            handleResponse(response)
        }
    }

    private fun handleResponse(response: Resource<Genres>?) {
        when (response) {
            is Resource.Loading -> {
                // TODO implement loading
                Log.d("api_loading", "Loading 🚀")
            }
            is Resource.Error -> {
                Log.d("api_error", response.message.toString())
            }
            is Resource.Success -> {
                if (response.data != null) {
                     val tags = response.data.toptags.tag

                    // set the layout
                    binding.rvGenresList.layoutManager =
                        FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP)

                    // create an adapter
                    adapter = GenresAdapter(this, tags)

                    // on click listener on each genre
                    adapter.setOnClickListener(object: GenresAdapter.OnClickListener {
                        override fun onClick(position: Int) {
                            // go to the genre detail activity
                            val intent = Intent(this@MainActivity, GenreDetailActivity::class.java)
                            intent.putExtra(Constants.GENRE_NAME, tags[position].name) // pass the name of tag with the intent
                            startActivity(intent)
                        }
                    })

                    // set the adapter to the recycler view
                    binding.rvGenresList.adapter = adapter

                    // make the view more text view visible
                    binding.tvViewMore.visibility = TextView.VISIBLE
                }
            }
            else -> {

            }
        }
    }
}