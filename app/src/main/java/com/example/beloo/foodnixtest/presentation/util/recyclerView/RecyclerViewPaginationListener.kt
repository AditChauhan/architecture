package com.example.beloo.foodnixtest.presentation.util.recyclerView


import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @param offsetItemsCount offset of items count until end of list to request loading next page
 * @param footer View which will be attached to footerContainer when next page would start loading
 * @param footerContainer some container which could set and remove footer
 * @param totalItemsCount set total of items with which scrolled items count will be compared to start request loading next page
 */
class RecyclerViewPaginationListener private constructor(
    private val layoutManager: LinearLayoutManager,
    private val onLoadNextListener: OnLoadNextListener? = null,
    private val offsetItemsCount: Int = 0,
    private val footer: View? = null,
    private val footerContainer: FooterContainer? = null,
    private var totalItemsCount: Int? = null
) : RecyclerView.OnScrollListener() {

    private var isLoadingNextPage: Boolean = false
    private var isFinishedLoading: Boolean = false

    /**
     * @param isLoadingNextPage when this flag is set scrollListener doesn't call [OnLoadNextListener.onLoadNext].
     * Also removes footer from list if == false
     */
    fun setIsLoadingNextPage(isLoadingNextPage: Boolean) {
        this.isLoadingNextPage = isLoadingNextPage
        if (!isLoadingNextPage && footer != null && footerContainer != null) {
            footerContainer.removeFooter()
        }
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
        if (dy > 0) { //check for scroll down
            val visibleItemCount = layoutManager.childCount
            val totalItemsCount: Int =
                if (this.totalItemsCount == null) layoutManager.itemCount else this.totalItemsCount!!
            val loadNextPageItemsCount = totalItemsCount - offsetItemsCount
            val lastVisibleItems = layoutManager.findFirstVisibleItemPosition()

            val scrolledItemsCount = visibleItemCount + lastVisibleItems

            if (scrolledItemsCount >= loadNextPageItemsCount && !isLoadingNextPage && !isFinishedLoading) {
                if (footer != null && footerContainer != null) {
                    footerContainer.setFooter(footer)
                }

                onLoadNextListener?.onLoadNext()
            }
        }
    }

    interface OnLoadNextListener {
        fun onLoadNext()
    }

    interface FooterContainer {
        fun setFooter(footer: View)

        fun removeFooter()
    }
}
