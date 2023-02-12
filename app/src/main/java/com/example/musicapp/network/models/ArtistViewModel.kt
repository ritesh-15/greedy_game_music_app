package com.example.musicapp.network.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.artist_info.ArtistInfo
import com.example.musicapp.models.artists.Artists
import com.example.musicapp.models.genres.Genres
import com.example.musicapp.repository.AlbumsRepository
import com.example.musicapp.repository.ArtistsRepository
import com.example.musicapp.repository.GenresRepository
import com.example.musicapp.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class ArtistViewModel(
    private val repository: ArtistsRepository,
) : ViewModel() {
    // list of artists
    private val _artistsResponse = MutableLiveData<Resource<Artists>>()
    val artists: LiveData<Resource<Artists>>
        get() = _artistsResponse


    fun getArtistsList(tag:String) {
        viewModelScope.launch {
            _artistsResponse.value = Resource.Loading(null)
            val response = repository.getArtists(tag)
            try {
                if (response.isSuccessful) {
                    _artistsResponse.value = Resource.Success(response.body())
                } else {
                    _artistsResponse.value =
                        Resource.Error(response.code().toString(), response.code())
                }
            } catch (e: Exception) {
                Log.d("ERROR_RESPONSE", e.message!!)
                _artistsResponse.value = Resource.Error(response.code().toString(), response.code())
            }
        }
    }

    // list of artists
    private val _artistInfoResponse = MutableLiveData<Resource<ArtistInfo>>()
    val artistInfo: LiveData<Resource<ArtistInfo>>
        get() = _artistInfoResponse

    fun getArtistInfo(artist:String) {
        viewModelScope.launch {
            _artistInfoResponse.value = Resource.Loading(null)
            val response = repository.getArtistInfo(artist)
            try {
                if (response.isSuccessful) {
                    _artistInfoResponse.value = Resource.Success(response.body())
                } else {
                    _artistInfoResponse.value =
                        Resource.Error(response.code().toString(), response.code())
                }
            } catch (e: Exception) {
                Log.d("ERROR_RESPONSE", e.message!!)
                _artistInfoResponse.value = Resource.Error(response.code().toString(), response.code())
            }
        }
    }
}