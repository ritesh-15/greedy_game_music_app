package com.example.musicapp.models.tracks

import com.google.gson.annotations.SerializedName

data class TracksX(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)