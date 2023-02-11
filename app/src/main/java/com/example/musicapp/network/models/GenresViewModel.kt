package com.example.musicapp.network.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}