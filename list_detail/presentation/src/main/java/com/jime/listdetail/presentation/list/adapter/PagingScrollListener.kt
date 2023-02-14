package com.jime.listdetail.presentation.list.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagingScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val pageSize: Int = 25,
    private val threshold: Int = 2,
    val onPageFinished: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    var isLastPage = false
    var isLoading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
            && firstVisibleItemPosition >= 0
            && totalItemCount >= threshold
            && !isLastPage && !isLoading
        ) {
            val page = (totalItemCount / pageSize)
            onPageFinished(page)
        }
    }
}