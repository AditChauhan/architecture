package com.example.beloo.architecture.data.repository

import com.example.beloo.architecture.data.repository.producer.ProducersRepository
import com.example.beloo.architecture.data.repository.producer.ProducersDataRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun producersRepository(repository: ProducersDataRepositoryImpl): ProducersRepository
}
