package com.example.beloo.foodnixtest.presentation.restaurant

import com.example.beloo.foodnixtest.core.injection.ActivityScope
import com.example.beloo.foodnixtest.domain.producer.ProducersListUseCase
import com.example.beloo.foodnixtest.presentation.AbstractPresenter
import com.example.beloo.foodnixtest.presentation.SubscriptionCache
import javax.inject.Inject

@ActivityScope
class ProducersPresenter @Inject constructor(
	private val producersListUseCase: ProducersListUseCase,
	cache: SubscriptionCache
) : AbstractPresenter<ProducersContract.View>(cache), ProducersContract.Presenter {

	override fun onReady() {
		observeProducers()
		//todo undo
		onLoadNextPage()
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
		producersListUseCase.loadProducersChunk(0)
			.subscribeManaged(TAG_PRODUCERS_CHUNK)
	}

	companion object {
		private const val TAG_PRODUCERS = "producers"
		private const val TAG_PRODUCERS_CHUNK = "producers_chunk"
	}

}

