package com.example.musicapp.api.albums

import com.example.musicapp.constants.Constants
import com.example.musicapp.models.albums.Albums
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumInterface {

    @GET("?method=tag.gettopalbums&api_key=${Constants.API_KEY}&format=json")
    suspend fun getAlbums(@Query("tag") tag:String): Response<Albums>

}