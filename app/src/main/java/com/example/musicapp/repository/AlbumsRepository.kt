package com.example.musicapp.repository

import com.example.musicapp.api.APIInstance
import com.example.musicapp.models.albums.Albums
import retrofit2.Response

class AlbumsRepository {

    suspend fun getAlbums(tag:String):Response<Albums> {
        return APIInstance.albumsApi.getAlbums(tag)
    }

}