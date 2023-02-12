package com.jime.listdetail.domain.model

data class OompaLoompaPaging(
    val totalPages: Int,
    val currentPage: Int,
    val oompaLoompaList: List<OompaLoompa>
) {
}