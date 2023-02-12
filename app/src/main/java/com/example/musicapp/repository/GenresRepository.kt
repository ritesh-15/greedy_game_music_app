package com.example.musicapp.repository

import com.example.musicapp.api.APIInstance
import com.example.musicapp.models.genre_details.GenreDetails
import com.example.musicapp.models.genres.Genres
import retrofit2.Response

class GenresRepository {

    suspend fun getGenres(): Response<Genres> {
        return APIInstance.genresApi.getGenres()
    }

    suspend fun getGenreDetails(tag:String): Response<GenreDetails> {
        return APIInstance.genresApi.getDetails(tag)
    }

}