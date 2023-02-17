package com.jime.listdetail.presentation.list.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagingScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val pageSize: Int = 25,
    private val countMargin: Int = 5,
    private val onPageFinished: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    var isLastPage = false
    var isLoading = false
    var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - countMargin
            && firstVisibleItemPosition >= 0
            && !isLastPage
            && !isLoading
        ) {
            onPageFinished(currentPage)
        }
    }
}