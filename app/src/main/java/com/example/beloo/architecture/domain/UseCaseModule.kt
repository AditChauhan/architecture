package com.example.beloo.architecture.domain

import com.example.beloo.architecture.domain.producer.ProducersListUseCase
import com.example.beloo.architecture.domain.producer.ProducersListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal interface UseCaseModule {
    @Binds
    fun producersUseCase(useCase: ProducersListUseCaseImpl): ProducersListUseCase
}