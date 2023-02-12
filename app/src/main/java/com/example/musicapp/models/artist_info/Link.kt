package com.example.musicapp.models.artist_info

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("#text")
    val text: String,
    val href: String,
    val rel: String
)