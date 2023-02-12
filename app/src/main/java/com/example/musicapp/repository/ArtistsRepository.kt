package com.example.musicapp.repository

import com.example.musicapp.api.APIInstance
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.artist_info.ArtistInfo
import com.example.musicapp.models.artists.Artists
import retrofit2.Response

class ArtistsRepository {

    suspend fun getArtists(tag:String):Response<Artists> {
        return APIInstance.artistsApi.getArtists(tag)
    }


    suspend fun getArtistInfo(artist:String):Response<ArtistInfo> {
        return APIInstance.artistsApi.getArtistInfo(artist)
    }

}