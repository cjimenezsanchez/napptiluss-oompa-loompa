package com.jime.listdetail.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.model.OompaLoompa
import com.jime.listdetail.domain.model.OompaLoompaPaging
import com.jime.listdetail.presentation.databinding.FragmentListBinding
import com.jime.listdetail.presentation.list.adapter.OompaLoompaListAdapter
import com.jime.listdetail.presentation.list.adapter.PagingScrollListener
import com.jime.listdetail.presentation.list.viewmodel.OompaLoompaListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: OompaLoompaListAdapter
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
        initList()
        fetchOompaLoompaPage()
        observeUiChanges()
    }
    private fun observeUiChanges() {
        viewModel.oompaLoompa.observe(viewLifecycleOwner) { result ->
            handleResult(result)
        }

        viewModel.navigateToDetail.observe(viewLifecycleOwner) { event ->
            event.getContentIfHasNotBeenHandled()?.let { id ->
                navigateToDetail(id)
            }
        }
    }

    private fun initList() {
        adapter = OompaLoompaListAdapter(
            onItemClicked = { id ->
                viewModel.onOompaLoompaClicked(id)
            })

        val recyclerView = binding.list
        val layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
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

    private fun handleResult(result: Resource<OompaLoompaPaging>) {
        when (result) {
            is Resource.Success -> {
                updatePaginatingInfo(result.data.currentPage, result.data.totalPages)
                updateOompaLoompaList(result.data.oompaLoompaList)
            }
            is Resource.Failure -> {

            }
        }
        pagingListener.isLoading = false
    }

    private fun updatePaginatingInfo(currentPage: Int, totalPages: Int) {
        if (currentPage == 4) {
            pagingListener.isLastPage = true
        }
    }

    private fun updateOompaLoompaList(list: List<OompaLoompa>) {
        adapter.addItems(list)
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