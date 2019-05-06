package com.example.beloo.foodnixtest.data.repository.producer

import android.util.Log
import com.example.beloo.foodnixtest.core.rx.RxIO
import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.language.TAG
import com.example.beloo.foodnixtest.network.producer.ProducerDataSource
import com.example.beloo.foodnixtest.storage.producer.ProducerDao
import com.example.beloo.foodnixtest.storage.producer.ProducerEntityMapper
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

interface ProducersRepository {
    fun producersStream(): Observable<List<Producer>>

    /** @return [Single], it emits true when received last page */
    fun loadProducersChunk(page: Int): Single<Boolean>
}

class ProducersDataRepositoryImpl @Inject constructor(
    private val dataSource: ProducerDataSource,
    @RxIO
    private val io: Scheduler,
    private val entityMapper: ProducerEntityMapper,
    private val dao: ProducerDao
) : ProducersRepository {

    override fun loadProducersChunk(page: Int): Single<Boolean> = dataSource
        .getProducers(page)
        .observeOn(io)
        .map { list -> list.map { entityMapper.map(it) } }
        .flatMap {
            Log.d(TAG, "store producers, count = ${it.count()}")
            dao.putAsync(it).andThen(Single.just(it))
        }
        .map { it.isEmpty() }

    override fun producersStream(): Observable<List<Producer>> = dao
        .producersStream()
        .subscribeOn(io)
        .doOnNext { Log.d(TAG, "producers updated, count = ${it.count()}") }
        .map { it.map { item -> item as Producer } }

}