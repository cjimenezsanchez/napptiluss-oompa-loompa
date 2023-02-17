package com.jime.listdetail.presentation.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.di.IoDispatcher
import com.jime.listdetail.domain.model.OompaLoompa
import com.jime.listdetail.domain.model.OompaLoompaPaging
import com.jime.listdetail.domain.model.Profession
import com.jime.listdetail.domain.model.Profession.Developer
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import com.jime.listdetail.presentation.list.Event
import com.jime.listdetail.presentation.list.filter.ProfessionFilter
import com.jime.listdetail.presentation.list.model.ListUiState
import com.jime.listdetail.presentation.list.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OompaLoompaListViewModel @Inject constructor(
    private val oompaLoompaRepository: OompaLoompaRepository
) : ViewModel() {

    private var _navigateToDetail: MutableLiveData<Event<Int>> = MutableLiveData()
    val navigateToDetail: LiveData<Event<Int>> get() = _navigateToDetail

    private var _currentFilter: MutableLiveData<ProfessionFilter> = MutableLiveData()
    val currentFilter: LiveData<ProfessionFilter> get() = _currentFilter

    private var _uiState: MutableLiveData<ListUiState> = MutableLiveData()
    val uiState: LiveData<ListUiState> get() = _uiState

    private var loadedPages: List<Int> = emptyList()
    private var oompaLoompaList: List<OompaLoompa> = emptyList()

    fun fetchOompaLoompaByPage(page: Int = 1) {
        if (loadedPages.contains(page)) {
            return
        }

        viewModelScope.launch {
            _uiState.value = ListUiState(State.LOADING)

            oompaLoompaRepository.fetchOompaLoompaByPage(page).let { result ->
                when (result) {
                    is Resource.Success -> {
                        onSuccessResult(result)
                    }
                    is Resource.Failure -> {
                        onFailedResult(result)
                    }
                }
            }
        }
    }

    private fun onSuccessResult(result: Resource.Success<OompaLoompaPaging>) {
        oompaLoompaList = oompaLoompaList + result.data.oompaLoompaList
        loadedPages = loadedPages + result.data.currentPage

        _uiState.value = ListUiState(
            state = State.LOADED,
            list = getFilteredList(oompaLoompaList, _currentFilter.value),
            totalPages = result.data.totalPages,
            currentPage = result.data.currentPage,
            selectedFilter = _currentFilter.value ?: ProfessionFilter.None
        )
    }

    private fun onFailedResult(result: Resource.Failure<OompaLoompaPaging>) {
        _uiState.value = ListUiState(
            state = State.ERROR,
            error = result.error,
            totalPages = _uiState.value?.totalPages ?: -1,
            currentPage = _uiState.value?.currentPage ?: 1
        )
    }

    private fun getFilteredList(
        list: List<OompaLoompa>,
        filter: ProfessionFilter?
    ): List<OompaLoompa> {
        return when (filter) {
            is ProfessionFilter.ByType -> {
                list.filter { it.profession == filter.type }
            }
            else -> list
        }
    }

    fun onFilterClicked(newFilter: ProfessionFilter) {
        _currentFilter.value = newFilter
        _uiState.value?.let {
            _uiState.value = it.copy(
                list = getFilteredList(oompaLoompaList, newFilter),
                selectedFilter = newFilter
            )
        }
    }

    fun onOompaLoompaClicked(id: Int) {
        _navigateToDetail.value = Event(id)
    }
}