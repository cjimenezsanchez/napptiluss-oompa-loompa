package com.jime.listdetail.data.remote.model

import com.google.gson.annotations.SerializedName

data class OompaLoompaDto(
    @SerializedName("id") val id: String,
    @SerializedName("first_name") val name: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("profession") val profession: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String
)
