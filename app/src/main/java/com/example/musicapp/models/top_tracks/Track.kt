package com.example.musicapp.models.top_tracks

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("@attr")
    val attr: AttrX,
    val artist: Artist,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    val streamable: String,
    val url: String
)