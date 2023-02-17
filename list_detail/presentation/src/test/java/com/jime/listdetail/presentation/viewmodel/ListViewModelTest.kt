package com.jime.listdetail.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jime.listdetail.domain.model.Profession
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import com.jime.listdetail.presentation.CoroutineTestRule
import com.jime.listdetail.presentation.detail.viewmodel.OompaLoompaDetailViewModel
import com.jime.listdetail.presentation.list.Event
import com.jime.listdetail.presentation.list.filter.ProfessionFilter
import com.jime.listdetail.presentation.list.model.ListUiState
import com.jime.listdetail.presentation.list.model.State
import com.jime.listdetail.presentation.list.viewmodel.OompaLoompaListViewModel
import com.jime.listdetail.presentation.repository.FakeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest {

    private lateinit var viewModel: OompaLoompaListViewModel
    private val fakeRepository: OompaLoompaRepository = FakeRepository()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = OompaLoompaListViewModel(fakeRepository)
    }

    @Test
    fun `ui state is updated after fetching data`() = runTest {

        val uiStateObserver: Observer<ListUiState> = mock()
        viewModel.uiState.observeForever(uiStateObserver)

        viewModel.fetchOompaLoompaByPage(1)
        assertEquals(viewModel.uiState.value?.state, State.LOADED)
    }

    @Test
    fun `current filter is updated when clicked`() {
        val filterObserver: Observer<ProfessionFilter> = mock()
        val newFilter = ProfessionFilter.ByType(Profession.Developer)

        viewModel.currentFilter.observeForever(filterObserver)
        viewModel.onFilterClicked(newFilter)

        verify(filterObserver).onChanged(newFilter)
    }

    @Test
    fun `navigates to item when clicked`() {
        val navigateObserver: Observer<Event<Int>> = mock()
        viewModel.navigateToDetail.observeForever(navigateObserver)

        viewModel.onOompaLoompaClicked(1)
        verify(navigateObserver).onChanged(Event(1))
    }

}