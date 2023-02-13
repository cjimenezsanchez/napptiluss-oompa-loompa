package com.jime.listdetail.presentation.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.model.OompaLoompaPaging
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OompaLoompaListViewModel @Inject constructor(
    private val oompaLoompaRepository: OompaLoompaRepository
) : ViewModel() {

    var oompaLoompa: MutableLiveData<Resource<OompaLoompaPaging>> = MutableLiveData()
        private set

    fun fetchOompaLoompaByPage() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                oompaLoompaRepository.fetchOompaLoompaByPage(1)
            }

            oompaLoompa.value = result
        }
    }


}