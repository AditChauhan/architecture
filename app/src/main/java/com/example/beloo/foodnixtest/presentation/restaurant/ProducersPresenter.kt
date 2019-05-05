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
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	companion object {
		private const val TAG_PRODUCERS = "producers"
	}

}

