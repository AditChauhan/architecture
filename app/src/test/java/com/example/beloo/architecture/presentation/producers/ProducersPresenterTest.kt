package com.example.beloo.architecture.presentation.producers

import com.example.beloo.architecture.domain.producer.ProducersListUseCase
import com.example.beloo.architecture.presentation.SubscriptionCacheImpl
import com.example.beloo.architecture.storage.producer.ProducerEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class ProducersPresenterTest {

    private val producers = listOf(
        ProducerEntity(5, "ABCD", "descr", "short")
    )

    private val producersListUseCase = mockk<ProducersListUseCase>().apply {
        every { producersStream() } returns Observable.just(producers)
        every { loadProducersChunk(any()) } returns Single.just(true)
    }

    private val testable: ProducersContract.Presenter by lazy {
        ProducersPresenter(
            producersListUseCase,
            SubscriptionCacheImpl(Schedulers.computation())
        )
    }

    private val view: ProducersContract.View = mockk(relaxed = true)

    @Test
    fun onReady_calls_loading_and_updates_view() {
        testable.bindView(view)
        verify { view.setData(producers) }
    }
}