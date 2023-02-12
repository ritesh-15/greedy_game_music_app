package com.example.musicapp.api.albums

import com.example.musicapp.constants.Constants
import com.example.musicapp.models.album_info.AlbumInfo
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.artists.TopArtists
import com.example.musicapp.models.top_albums.TopAlbums
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumInterface {
    @GET("?method=tag.gettopalbums&api_key=${Constants.API_KEY}&format=json")
    suspend fun getAlbums(@Query("tag") tag: String): Response<Albums>

    @GET("?method=artist.gettopalbums&artist=cher&api_key=${Constants.API_KEY}&format=json")
    suspend fun getTopAlbums(@Query("artist") artist: String): Response<TopAlbums>

    @GET("?method=album.getinfo&api_key=${Constants.API_KEY}&format=json")
    suspend fun getAlbumInfo(
        @Query("artist") artist: String,
        @Query("album") album: String,
    ): Response<AlbumInfo>
}