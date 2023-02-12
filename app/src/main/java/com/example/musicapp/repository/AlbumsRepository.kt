package com.example.musicapp.repository

import com.example.musicapp.api.APIInstance
import com.example.musicapp.models.album_info.AlbumInfo
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.artists.TopArtists
import com.example.musicapp.models.top_albums.TopAlbums
import retrofit2.Response

class AlbumsRepository {
    suspend fun getAlbums(tag:String):Response<Albums> {
        return APIInstance.albumsApi.getAlbums(tag)
    }

    suspend fun getAlbumInfo(artist:String, album:String):Response<AlbumInfo> {
        return APIInstance.albumsApi.getAlbumInfo(artist, album)
    }

    suspend fun getTopAlbums(artist:String):Response<TopAlbums> {
        return APIInstance.albumsApi.getTopAlbums(artist)
    }

}