package com.example.musicapp.api.genre

import com.example.musicapp.constants.Constants
import com.example.musicapp.models.genre_details.GenreDetails
import com.example.musicapp.models.genres.Genres
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

interface GenreInterface {

    @GET("?method=tag.getTopTags&api_key=${Constants.API_KEY}&format=json")
    suspend fun getGenres():Response<Genres>


    @GET("?method=tag.getinfo&api_key=${Constants.API_KEY}&format=json")
    suspend fun getDetails(@Query("tag") tag:String):Response<GenreDetails>

}