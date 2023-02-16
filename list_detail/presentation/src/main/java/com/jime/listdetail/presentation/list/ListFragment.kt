package com.jime.listdetail.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jime.listdetail.domain.error.Error
import com.jime.listdetail.domain.model.OompaLoompa
import com.jime.listdetail.presentation.databinding.FragmentListBinding
import com.jime.listdetail.presentation.list.adapter.FilterAdapter
import com.jime.listdetail.presentation.list.adapter.OompaLoompaListAdapter
import com.jime.listdetail.presentation.list.adapter.PagingScrollListener
import com.jime.listdetail.presentation.list.model.ListUiState
import com.jime.listdetail.presentation.list.model.State
import com.jime.listdetail.presentation.list.viewmodel.OompaLoompaListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: OompaLoompaListAdapter
    private lateinit var filterAdapter: FilterAdapter
    private lateinit var pagingListener: PagingScrollListener

    private val viewModel: OompaLoompaListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFilterList()
        initList()
        observeUiChanges()
        fetchOompaLoompaPage()
    }
    private fun observeUiChanges() {
        viewModel.currentFilter.observe(viewLifecycleOwner) {filter ->
            filterAdapter.updateSelectedFilter(filter)
        }

        viewModel.uiState.observe(viewLifecycleOwner) {uiState ->
            handleNewUiState(uiState)
        }

        viewModel.navigateToDetail.observe(viewLifecycleOwner) { event ->
            event.getContentIfHasNotBeenHandled()?.let { id ->
                navigateToDetail(id)
            }
        }
    }

    private fun initFilterList() {
        filterAdapter = FilterAdapter {
            viewModel.onFilterClicked(it)
        }
        val filterRecyclerView = binding.filterList
        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        filterRecyclerView.adapter = filterAdapter
        filterRecyclerView.layoutManager = layoutManager
    }

    private fun initList() {
        listAdapter = OompaLoompaListAdapter(
            onItemClicked = { id ->
                viewModel.onOompaLoompaClicked(id)
            })

        val recyclerView = binding.list
        val layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = layoutManager

        pagingListener = PagingScrollListener(
            layoutManager = layoutManager,
            onPageFinished = { page ->
                fetchOompaLoompaPage(page + 1)
            }
        )

        recyclerView.addOnScrollListener(pagingListener)
    }

    private fun fetchOompaLoompaPage(page: Int = 1) {
        pagingListener.isLoading = true
        viewModel.fetchOompaLoompaByPage(page)
    }

    private fun handleNewUiState(uiState: ListUiState) {
        when (uiState.state) {
            State.LOADING -> {
                onLoading()
            }
            State.LOADED -> {
                onFinishLoading()
                updatePaginatingInfo(uiState.currentPage, uiState.totalPages)
                updateOompaLoompaList(uiState.list)
            }
            State.ERROR -> {
                onFinishLoading()
                showError(uiState.error)
                updatePaginatingInfo(uiState.currentPage, uiState.totalPages)
            }
        }
    }

    private fun onLoading() {
        listAdapter.onLoadingItems()
    }

    private fun onFinishLoading() {
        listAdapter.onFinishedLoadingItems()
    }

    private fun showError(error: Error?) {
        error?.let {
            when (error) {
                Error.Network -> {}
                Error.Unknown -> {}
                Error.NotFound -> {}
                Error.ServiceUnavailable -> {}
                Error.UnAuthorized -> {}
            }
        }
    }

    private fun updatePaginatingInfo(currentPage: Int, totalPages: Int) {
        pagingListener.isLoading = false
        pagingListener.currentPage = currentPage
        if (currentPage == totalPages) {
            pagingListener.isLastPage = true
        }
    }

    private fun updateOompaLoompaList(list: List<OompaLoompa>) {
        listAdapter.addItems(list)
    }

    private fun navigateToDetail(id: Int) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}