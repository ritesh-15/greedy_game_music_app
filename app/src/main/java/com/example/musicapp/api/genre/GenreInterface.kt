package com.example.musicapp.api.genre

import com.example.musicapp.constants.Constants
import com.example.musicapp.models.genres.Genres
import retrofit2.Response
import retrofit2.http.GET

interface GenreInterface {

    @GET("?method=tag.getTopTags&api_key=${Constants.API_KEY}&format=json")
    suspend fun getGenres():Response<Genres>

}