package com.example.beloo.architecture.network.producer

import android.util.Log
import com.example.beloo.architecture.core.rx.RxNetwork
import com.example.beloo.architecture.data.model.producer.Producer
import com.example.beloo.architecture.language.TAG
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

internal class ProducerRemoteSource @Inject constructor(
    private val repositoryApi: ProducersApi,
    @RxNetwork
    private val scheduler: Scheduler
) : ProducerDataSource {

    override fun getProducers(page: Int): Single<List<Producer>> = repositoryApi
        .getProducers(page)
        .map { it.producers as List<Producer> }
        .doOnSuccess { Log.d(TAG, "producers obtained for page = $page") }
        .subscribeOn(scheduler)

}
