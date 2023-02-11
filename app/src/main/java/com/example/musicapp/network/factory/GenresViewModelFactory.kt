package com.example.musicapp.network.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.network.models.GenresViewModel
import com.example.musicapp.repository.GenresRepository

class GenresViewModelFactory(
    private val repository: GenresRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GenresViewModel(repository) as T
    }
}