package com.jime.listdetail.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.model.OompaLoompaDetail
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import com.jime.listdetail.presentation.CoroutineTestRule
import com.jime.listdetail.presentation.detail.viewmodel.OompaLoompaDetailViewModel
import com.jime.listdetail.presentation.list.Event
import com.jime.listdetail.presentation.repository.FakeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    private lateinit var viewModel: OompaLoompaDetailViewModel
    private val fakeRepository: OompaLoompaRepository = FakeRepository()
    private val progressObserver: Observer<Boolean> = mock()
    private val detailObserver: Observer<Resource<OompaLoompaDetail>> = mock()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
       viewModel = OompaLoompaDetailViewModel(fakeRepository)
    }

    @Test()
    fun `progress updated when fetching oompa loompa detail`() = runTest {
        viewModel.progressVisible.observeForever(progressObserver)

        viewModel.getOompaLoompaById(1)

        verify(progressObserver).onChanged(true)
        verify(progressObserver).onChanged(false)
    }

    @Test()
    fun `updates ui state when fetching data`() = runTest {
        viewModel.oompaLoompa.observeForever(detailObserver)
        viewModel.progressVisible.observeForever(progressObserver)

        viewModel.getOompaLoompaById(1)

        verify(detailObserver).onChanged(fakeRepository.fetchOompaLoompaById(1))
    }

}

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
