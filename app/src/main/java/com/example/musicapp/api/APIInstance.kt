package com.example.musicapp.api

import com.example.musicapp.api.albums.AlbumInterface
import com.example.musicapp.api.artists.ArtistsInterface
import com.example.musicapp.api.genre.GenreInterface
import com.example.musicapp.api.tracks.TracksInterface
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

    val albumsApi:AlbumInterface by lazy {
        retrofit.create(AlbumInterface::class.java)
    }

    val artistsApi:ArtistsInterface by lazy {
        retrofit.create(ArtistsInterface::class.java)
    }

    val tracksApi:TracksInterface by lazy {
        retrofit.create(TracksInterface::class.java)
    }
}