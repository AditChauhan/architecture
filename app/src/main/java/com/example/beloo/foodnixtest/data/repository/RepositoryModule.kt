package com.example.beloo.foodnixtest.data.repository

import com.example.beloo.foodnixtest.data.repository.producer.ProducersRepository
import com.example.beloo.foodnixtest.data.repository.producer.ProducersDataRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun producersRepository(repository: ProducersDataRepositoryImpl): ProducersRepository
}
