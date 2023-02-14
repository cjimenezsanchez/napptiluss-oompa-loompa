package com.jime.listdetail.data.remote.model

import com.google.gson.annotations.SerializedName

data class OompaLoompaDetailDto(
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val name: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("profession") val profession: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String,
    @SerializedName("age") val age: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("description") val description: String,
    @SerializedName("quota") val quota: String,
    @SerializedName("email") val email: String,
    @SerializedName("country") val country: String,
    @SerializedName("favorite") val favoriteStuff: FavoriteDto
)
