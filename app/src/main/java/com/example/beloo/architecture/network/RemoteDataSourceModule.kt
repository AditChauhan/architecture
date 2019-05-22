package com.example.beloo.architecture.network

import com.example.beloo.architecture.network.producer.ProducerRemoteSource
import com.example.beloo.architecture.network.producer.ProducerDataSource
import dagger.Binds
import dagger.Module

@Module
internal interface RemoteDataSourceModule {

	@Binds
	fun provideRestaurantsSource(producersRemoteSource: ProducerRemoteSource): ProducerDataSource

}
