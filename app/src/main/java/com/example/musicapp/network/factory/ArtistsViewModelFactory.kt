package com.example.musicapp.network.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.network.models.AlbumsViewModel
import com.example.musicapp.network.models.ArtistViewModel
import com.example.musicapp.network.models.GenresViewModel
import com.example.musicapp.repository.AlbumsRepository
import com.example.musicapp.repository.ArtistsRepository
import com.example.musicapp.repository.GenresRepository

class ArtistsViewModelFactory(
    private val repository: ArtistsRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtistViewModel(repository) as T
    }
}