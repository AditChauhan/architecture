package com.example.beloo.foodnixtest.network.producer

import com.example.beloo.foodnixtest.core.rx.RxNetwork
import com.example.beloo.foodnixtest.data.model.producer.Producer
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
        .subscribeOn(scheduler)

}
