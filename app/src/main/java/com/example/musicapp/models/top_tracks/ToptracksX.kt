package com.example.musicapp.models.top_tracks

import com.google.gson.annotations.SerializedName

data class ToptracksX(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)