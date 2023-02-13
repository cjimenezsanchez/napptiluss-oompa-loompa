package com.jime.listdetail.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.presentation.databinding.FragmentListBinding
import com.jime.listdetail.presentation.list.adapter.OompaLoompaListAdapter
import com.jime.listdetail.presentation.list.viewmodel.OompaLoompaListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: OompaLoompaListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.list
        val adapter = OompaLoompaListAdapter()
        recyclerView.adapter = adapter

        viewModel.fetchOompaLoompaByPage()
        viewModel.oompaLoompa.observe(viewLifecycleOwner) {result ->
            when (result) {
                is Resource.Success -> {
                    adapter.addItems(result.data.oompaLoompaList)
                }
                is Resource.Failure -> {

                }
            }
        }
    }

}