package com.example.beloo.foodnixtest.network

import com.example.beloo.foodnixtest.network.producer.ProducerRemoteSource
import com.example.beloo.foodnixtest.network.producer.ProducerDataSource
import dagger.Binds
import dagger.Module

@Module
internal interface RemoteDataSourceModule {

	@Binds
	fun provideRestaurantsSource(producersRemoteSource: ProducerRemoteSource): ProducerDataSource

}
