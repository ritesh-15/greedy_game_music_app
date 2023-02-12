package com.example.musicapp.network.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.models.genre_details.GenreDetails
import com.example.musicapp.models.genres.Genres
import com.example.musicapp.repository.GenresRepository
import com.example.musicapp.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class GenresViewModel(
    private val repository:GenresRepository
):ViewModel () {
    // list of genres
    private val _genresResponse = MutableLiveData<Resource<Genres>>()
    val genres:LiveData<Resource<Genres>>
    get() = _genresResponse

    init {
        getGenreList()
    }

    fun getGenreList() {
        viewModelScope.launch {
            _genresResponse.value = Resource.Loading(null)
            val response = repository.getGenres()
            try {
                if (response.isSuccessful) {
                    _genresResponse.value =  Resource.Success(response.body())
                } else {
                    _genresResponse.value = Resource.Error(response.code().toString(),response.code())
                }
            }catch (e:Exception) {
                Log.d("ERROR_RESPONSE",e.message!!)
                _genresResponse.value = Resource.Error(response.code().toString(),response.code())
            }
        }
    }


    // genre details
    private val _genreDetailsResponse = MutableLiveData<Resource<GenreDetails>>()
    val genreDetails:LiveData<Resource<GenreDetails>>
        get() = _genreDetailsResponse

    fun getGenreDetails(tag:String) {
        viewModelScope.launch {
            _genreDetailsResponse.value = Resource.Loading(null)
            val response = repository.getGenreDetails(tag)
            try {
                if (response.isSuccessful) {
                    _genreDetailsResponse.value =  Resource.Success(response.body())
                } else {
                    _genreDetailsResponse.value = Resource.Error(response.code().toString(),response.code())
                }
            }catch (e:Exception) {
                Log.d("ERROR_RESPONSE",e.message!!)
                _genreDetailsResponse.value = Resource.Error(response.code().toString(),response.code())
            }
        }
    }
}