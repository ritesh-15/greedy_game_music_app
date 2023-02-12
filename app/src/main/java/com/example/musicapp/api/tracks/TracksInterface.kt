package com.example.musicapp.api.tracks

import com.example.musicapp.constants.Constants
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.artists.Artists
import com.example.musicapp.models.tracks.Tracks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TracksInterface {

    @GET("?method=tag.gettoptracks&api_key=${Constants.API_KEY}&format=json")
    suspend fun getTracks(@Query("tag") tag: String): Response<Tracks>

}