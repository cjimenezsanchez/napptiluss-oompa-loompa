package com.jime.listdetail.presentation.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.di.IoDispatcher
import com.jime.listdetail.domain.model.OompaLoompaPaging
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import com.jime.listdetail.presentation.list.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OompaLoompaListViewModel @Inject constructor(
    private val oompaLoompaRepository: OompaLoompaRepository,
    @IoDispatcher private val iODispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _oompaLoompa: MutableLiveData<Resource<OompaLoompaPaging>> = MutableLiveData()
    val oompaLoompa: LiveData<Resource<OompaLoompaPaging>> = _oompaLoompa

    private var _navigateToDetail: MutableLiveData<Event<Int>> = MutableLiveData()
    val navigateToDetail: LiveData<Event<Int>> = _navigateToDetail

    private var totalPages = 0

    fun fetchOompaLoompaByPage(page: Int = 1) {
        viewModelScope.launch {
            val result = withContext(iODispatcher) {
                oompaLoompaRepository.fetchOompaLoompaByPage(page)
            }

            if (result is Resource.Success) {
                totalPages = result.data.totalPages
            }

            _oompaLoompa.value = result
        }
    }

    fun onOompaLoompaClicked(id: Int) {
        _navigateToDetail.value = Event(id)
    }


}