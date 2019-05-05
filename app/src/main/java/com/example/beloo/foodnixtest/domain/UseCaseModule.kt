package com.example.beloo.foodnixtest.domain

import com.example.beloo.foodnixtest.domain.producer.ProducersListUseCase
import com.example.beloo.foodnixtest.domain.producer.ProducersListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {
    @Binds
    fun producersUseCase(useCase: ProducersListUseCaseImpl): ProducersListUseCase
}