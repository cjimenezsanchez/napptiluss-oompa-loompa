package com.jime.listdetail.presentation.list.model

import com.jime.listdetail.domain.model.OompaLoompa
import com.jime.listdetail.presentation.list.filter.ProfessionFilter
import com.jime.listdetail.domain.error.Error

data class ListUiState(
    val state: State = State.LOADING,
    val list: List<OompaLoompa> = emptyList(),
    val totalPages: Int = -1,
    val currentPage: Int = 1,
    val selectedFilter: ProfessionFilter = ProfessionFilter.None,
    val error: Error? = null
)

enum class State {
    LOADING, LOADED, ERROR
}
