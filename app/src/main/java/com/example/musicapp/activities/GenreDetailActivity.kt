package com.example.musicapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import com.example.musicapp.databinding.ActivityGenreDetailBinding
import com.example.musicapp.fragments.GenreDetailFragmentAdapter
import com.google.android.material.tabs.TabLayout

class GenreDetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityGenreDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentAdapter = GenreDetailFragmentAdapter(supportFragmentManager)
        binding.vpTabs.adapter = fragmentAdapter

        binding.tlGenres.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    Log.d("tab_pos", "${tab.position}")
                    binding.vpTabs.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    Log.d("tab_pos", "${tab.position}")
                    binding.vpTabs.currentItem = tab.position
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    Log.d("tab_pos", "${tab.position}")
                    binding.vpTabs.currentItem = tab.position
                }
            }

        })
    }
}