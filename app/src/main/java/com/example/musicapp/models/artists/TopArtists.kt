package com.example.musicapp.models.artists

import com.google.gson.annotations.SerializedName

data class TopArtists(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: List<Artist>
)