package com.example.musicapp.models.artists

import com.google.gson.annotations.SerializedName

data class Topartists(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: List<Artist>
)