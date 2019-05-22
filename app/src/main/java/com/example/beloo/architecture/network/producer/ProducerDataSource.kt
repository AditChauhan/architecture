package com.example.beloo.architecture.network.producer

import com.example.beloo.architecture.data.model.producer.Producer
import io.reactivex.Single

interface ProducerDataSource {
	fun getProducers(page: Int): Single<List<Producer>>
}