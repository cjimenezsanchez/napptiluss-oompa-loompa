package com.jime.listdetail.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.model.OompaLoompaPaging
import com.jime.listdetail.presentation.databinding.FragmentListBinding
import com.jime.listdetail.presentation.list.adapter.OompaLoompaListAdapter
import com.jime.listdetail.presentation.list.viewmodel.OompaLoompaListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: OompaLoompaListAdapter

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

        val recyclerView = binding.list
        adapter = OompaLoompaListAdapter(
            onItemClicked = { id ->
                viewModel.onOompaLoompaClicked(id)
            })
        recyclerView.adapter = adapter

        viewModel.fetchOompaLoompaByPage()
        viewModel.oompaLoompa.observe(viewLifecycleOwner) { result ->
            handleResult(result)
        }

        viewModel.navigateToDetail.observe(viewLifecycleOwner) { event ->
            event.getContentIfHasNotBeenHandled()?.let { id ->
                navigateToDetail(id)
            }
        }
    }

    private fun handleResult(result: Resource<OompaLoompaPaging>) {
        when (result) {
            is Resource.Success -> {
                adapter.addItems(result.data.oompaLoompaList)
            }
            is Resource.Failure -> {

            }
        }
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