package com.example.musicapp.network.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.genres.Genres
import com.example.musicapp.models.top_tracks.TopTracks
import com.example.musicapp.models.tracks.Tracks
import com.example.musicapp.repository.AlbumsRepository
import com.example.musicapp.repository.GenresRepository
import com.example.musicapp.repository.TracksRepository
import com.example.musicapp.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class TracksViewModel(
    private val repository: TracksRepository,
) : ViewModel() {
    // list of tracks
    private val _tracksResponse = MutableLiveData<Resource<Tracks>>()
    val tracks: LiveData<Resource<Tracks>>
        get() = _tracksResponse


    fun getTracks(tag:String) {
        viewModelScope.launch {
            _tracksResponse.value = Resource.Loading(null)
            val response = repository.getTracks(tag)
            try {
                if (response.isSuccessful) {
                    _tracksResponse.value = Resource.Success(response.body())
                } else {
                    _tracksResponse.value =
                        Resource.Error(response.code().toString(), response.code())
                }
            } catch (e: Exception) {
                Log.d("ERROR_RESPONSE", e.message!!)
                _tracksResponse.value = Resource.Error(response.code().toString(), response.code())
            }
        }
    }

    // list of top tracks
    private val _topTracksResponse = MutableLiveData<Resource<TopTracks>>()
    val topTracks: LiveData<Resource<TopTracks>>
        get() = _topTracksResponse


    fun getTopTracks(artist:String) {
        viewModelScope.launch {
            _topTracksResponse.value = Resource.Loading(null)
            val response = repository.getTopTracks(artist)
            try {
                if (response.isSuccessful) {
                    _topTracksResponse.value = Resource.Success(response.body())
                } else {
                    _topTracksResponse.value =
                        Resource.Error(response.code().toString(), response.code())
                }
            } catch (e: Exception) {
                Log.d("ERROR_RESPONSE", e.message!!)
                _topTracksResponse.value = Resource.Error(response.code().toString(), response.code())
            }
        }
    }
}