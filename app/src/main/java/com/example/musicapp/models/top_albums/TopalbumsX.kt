package com.example.musicapp.models.top_albums

import com.google.gson.annotations.SerializedName

data class TopalbumsX(
    @SerializedName("@attr")
    val attr: Attr,
    val album: List<Album>
)