package com.example.beloo.foodnixtest.presentation.util.recyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @param offsetItemsCount offset of items count until end of list to request loading next page
 * @param totalItemsCount set total of items with which scrolled items count will be compared to start request loading next page
 */
class RecyclerViewPaginationListener constructor(
    private val layoutManager: LinearLayoutManager,
    private val onLoadNext: (()->Unit)? = null,
    private val offsetItemsCount: Int = 4,
    var totalItemsCount: Int? = null
) : RecyclerView.OnScrollListener() {

    private var isLoadingNextPage: Boolean = false
    private var isFinishedLoading: Boolean = false

    /**
     * @param isLoadingNextPage when this flag is set scrollListener doesn't call [OnLoadNextListener.onLoadNext].
     */
    fun setIsLoadingNextPage(isLoadingNextPage: Boolean) {
        this.isLoadingNextPage = isLoadingNextPage
    }

    /**
     * when this method is called scrollListener won't call [OnLoadNextListener.onLoadNext] anymore.
     */
    fun setLoadingFinished() {
        this.isFinishedLoading = true
    }

    /**
     * call this method by example when data would be reloaded by pull to refresh
     */
    fun startLoadFromBeginning() {
        setIsLoadingNextPage(false)
        this.isFinishedLoading = false
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val visibleItemCount = layoutManager.childCount
        val totalItemsCount: Int =
            if (this.totalItemsCount == null) layoutManager.itemCount else this.totalItemsCount!!
        val loadNextPageItemsCount = totalItemsCount - offsetItemsCount
        val lastVisibleItems = layoutManager.findFirstVisibleItemPosition()

        val scrolledItemsCount = visibleItemCount + lastVisibleItems

        if (scrolledItemsCount >= loadNextPageItemsCount && !isLoadingNextPage && !isFinishedLoading) {
            onLoadNext?.invoke()
            setIsLoadingNextPage(true)
        }
    }

}
