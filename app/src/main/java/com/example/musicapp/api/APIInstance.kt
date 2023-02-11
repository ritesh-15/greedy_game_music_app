package com.example.musicapp.api

import com.example.musicapp.api.genre.GenreInterface
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIInstance {

    private const val BASE_URL = "https://ws.audioscrobbler.com/2.0/"

    private val retrofit: Retrofit by lazy {
        val json = GsonBuilder().create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(json))
            .build()
    }

     val genresApi:GenreInterface by lazy {
        retrofit.create(GenreInterface::class.java)
    }
}