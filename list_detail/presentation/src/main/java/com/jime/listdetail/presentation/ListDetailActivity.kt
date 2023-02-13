package com.jime.listdetail.presentation

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jime.listdetail.presentation.list.viewmodel.OompaLoompaListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListDetailActivity: AppCompatActivity(R.layout.activity_main) {

    private val viewModel: OompaLoompaListViewModel by viewModels()
    override fun onResume() {
        super.onResume()

        viewModel.fetchOompaLoompaByPage()

    }
}