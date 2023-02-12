package com.example.musicapp.network.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.genres.Genres
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
}