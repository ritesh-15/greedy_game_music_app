package com.example.musicapp.network.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.models.album_info.AlbumInfo
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.genres.Genres
import com.example.musicapp.models.top_albums.TopAlbums
import com.example.musicapp.repository.AlbumsRepository
import com.example.musicapp.repository.GenresRepository
import com.example.musicapp.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class AlbumsViewModel(
    private val repository: AlbumsRepository,
) : ViewModel() {
    // list of albums
    private val _albumsResponse = MutableLiveData<Resource<Albums>>()
    val albums: LiveData<Resource<Albums>>
        get() = _albumsResponse


    fun getAlbumsList(tag:String) {
        viewModelScope.launch {
            _albumsResponse.value = Resource.Loading(null)
            val response = repository.getAlbums(tag)
            try {
                if (response.isSuccessful) {
                    _albumsResponse.value = Resource.Success(response.body())
                } else {
                    _albumsResponse.value =
                        Resource.Error(response.code().toString(), response.code())
                }
            } catch (e: Exception) {
                Log.d("ERROR_RESPONSE", e.message!!)
                _albumsResponse.value = Resource.Error(response.code().toString(), response.code())
            }
        }
    }


    // album info
    private val _albumInfoResponse = MutableLiveData<Resource<AlbumInfo>>()
    val albumInfo: LiveData<Resource<AlbumInfo>>
        get() = _albumInfoResponse
    fun getAlbumInfo(artist:String, album:String) {
        viewModelScope.launch {
            _albumInfoResponse.value = Resource.Loading(null)
            val response = repository.getAlbumInfo(artist, album)
            try {
                if (response.isSuccessful) {
                    _albumInfoResponse.value = Resource.Success(response.body())
                } else {
                    _albumInfoResponse.value =
                        Resource.Error(response.code().toString(), response.code())
                }
            } catch (e: Exception) {
                Log.d("ERROR_RESPONSE", e.message!!)
                _albumInfoResponse.value = Resource.Error(response.code().toString(), response.code())
            }
        }
    }


    // album info
    private val _topAlbumsResponse = MutableLiveData<Resource<TopAlbums>>()
    val topAlbums: LiveData<Resource<TopAlbums>>
        get() = _topAlbumsResponse

    fun getTopAlbums(artist:String) {
        viewModelScope.launch {
            _topAlbumsResponse.value = Resource.Loading(null)
            val response = repository.getTopAlbums(artist)
            try {
                if (response.isSuccessful) {
                    _topAlbumsResponse.value = Resource.Success(response.body())
                } else {
                    _topAlbumsResponse.value =
                        Resource.Error(response.code().toString(), response.code())
                }
            } catch (e: Exception) {
                Log.d("ERROR_RESPONSE", e.message!!)
                _topAlbumsResponse.value = Resource.Error(response.code().toString(), response.code())
            }
        }
    }
}