package com.example.beloo.foodnixtest.network.producer

import com.example.beloo.foodnixtest.data.model.producer.Producer
import io.reactivex.Single

interface ProducerDataSource {
	fun getProducers(page: Int): Single<List<Producer>>
}