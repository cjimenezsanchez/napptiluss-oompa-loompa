package com.jime.listdetail.data.remote.model

import com.google.gson.annotations.SerializedName

data class OompaLoompaPagingDto(
    @SerializedName("current") val currentPage: Int,
    @SerializedName("total") val totalPages: Int,
    @SerializedName("results") val results: List<OompaLoompaDto>
)
