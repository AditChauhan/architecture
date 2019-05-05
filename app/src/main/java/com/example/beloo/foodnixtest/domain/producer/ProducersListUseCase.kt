package com.example.beloo.foodnixtest.domain.producer

import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.data.repository.producer.ProducersRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

interface ProducersListUseCase {
    fun producersStream(): Observable<List<Producer>>

    fun loadProducersChunk(page: Int): Single<Boolean>
}

internal class ProducersListUseCaseImpl @Inject constructor(
    private val repository: ProducersRepository
) : ProducersListUseCase {

    override fun producersStream(): Observable<List<Producer>> = repository
        .producersStream()

    override fun loadProducersChunk(page: Int): Single<Boolean> = repository
        .loadProducersChunk(page)

}