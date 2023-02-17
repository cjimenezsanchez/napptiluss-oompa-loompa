package com.jime.listdetail.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.di.IoDispatcher
import com.jime.listdetail.domain.model.OompaLoompaDetail
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class OompaLoompaDetailViewModel @Inject constructor(
    private val oompaLoompaRepository: OompaLoompaRepository
) : ViewModel() {

    private var _oompaLoompa: MutableLiveData<Resource<OompaLoompaDetail>> = MutableLiveData()
    val oompaLoompa: LiveData<Resource<OompaLoompaDetail>> get() = _oompaLoompa

    private var _progressVisible: MutableLiveData<Boolean> = MutableLiveData()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    fun getOompaLoompaById(id: Int) {
        if (oompaLoompaAlreadyLoaded()) {
            return
        }
        viewModelScope.launch {
            _progressVisible.value = true
            _oompaLoompa.value = oompaLoompaRepository.fetchOompaLoompaById(id)
            _progressVisible.value = false
        }
    }

    private fun oompaLoompaAlreadyLoaded() = oompaLoompa.value is Resource.Success

}