package com.example.beloo.architecture.presentation.producers

import com.example.beloo.architecture.core.injection.ActivityScope
import com.example.beloo.architecture.domain.producer.ProducersListUseCase
import com.example.beloo.architecture.presentation.AbstractPresenter
import com.example.beloo.architecture.presentation.SubscriptionCache
import javax.inject.Inject

@ActivityScope
class ProducersPresenter @Inject constructor(
    private val producersListUseCase: ProducersListUseCase,
    cache: SubscriptionCache
) : AbstractPresenter<ProducersContract.View>(cache), ProducersContract.Presenter {

    var page: Int = 0

    override fun onReady() {
        observeProducers()
        if (page == 0) {
            onLoadNextPage()
        }
    }

    private fun observeProducers() {
        if (isSubscribed(TAG_PRODUCERS)) return
        view.showProgress()
        producersListUseCase.producersStream()
            .subscribeManaged(
                TAG_PRODUCERS,
                {
                    view.hideProgress()
                    view.setData(it)
                },
                {
                    view.hideProgress()
                    view.showError(it)
                })
    }

    override fun onLoadNextPage() {
        if (isSubscribed(TAG_PRODUCERS_CHUNK)) return
        view.showProgress()
        producersListUseCase.loadProducersChunk(page)
            .subscribeManaged(
                TAG_PRODUCERS_CHUNK,
                {isLoadedCompletely ->
                    page++
                    view.hideProgress()
                    if (isLoadedCompletely) {
                        view.setItemsLoadedCompletely()
                    }
                },
                {
                    view.hideProgress()
                    view.showError(it)
                })
    }

    companion object {
        private const val TAG_PRODUCERS = "producers"
        private const val TAG_PRODUCERS_CHUNK = "producers_chunk"
    }

}

