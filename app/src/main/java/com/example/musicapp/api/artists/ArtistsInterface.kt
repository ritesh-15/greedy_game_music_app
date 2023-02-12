package com.example.musicapp.api.artists

import com.example.musicapp.constants.Constants
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.artist_info.ArtistInfo
import com.example.musicapp.models.artists.Artists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistsInterface {
    @GET("?method=tag.gettopartists&api_key=${Constants.API_KEY}&format=json")
    suspend fun getArtists(@Query("tag") tag:String): Response<Artists>

    @GET("?method=artist.getinfo&api_key=${Constants.API_KEY}&format=json")
    suspend fun getArtistInfo(@Query("artist") artist:String): Response<ArtistInfo>
}