package com.example.musicapp.repository

import com.example.musicapp.api.APIInstance
import com.example.musicapp.models.albums.Albums
import com.example.musicapp.models.top_tracks.TopTracks
import com.example.musicapp.models.tracks.Tracks
import retrofit2.Response

class TracksRepository {

    suspend fun getTracks(tag:String):Response<Tracks> {
        return APIInstance.tracksApi.getTracks(tag)
    }

    suspend fun getTopTracks(artist:String):Response<TopTracks> {
        return APIInstance.tracksApi.getTopTracks(artist)
    }

}