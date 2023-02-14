package com.jime.listdetail.data.remote.model

import com.google.gson.annotations.SerializedName

data class FavoriteDto(
    @SerializedName("color") val color: String,
    @SerializedName("food") val food: String,
    @SerializedName("song") val song: String
)
