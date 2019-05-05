package com.example.beloo.foodnixtest.core.injection


import com.example.beloo.foodnixtest.Application
import com.example.beloo.foodnixtest.core.rx.RxModule
import com.example.beloo.foodnixtest.data.repository.RepositoryModule
import com.example.beloo.foodnixtest.domain.UseCaseModule
import com.example.beloo.foodnixtest.network.JsonConverterModule
import com.example.beloo.foodnixtest.network.NetworkApiModule
import com.example.beloo.foodnixtest.network.RemoteDataSourceModule
import com.example.beloo.foodnixtest.network.producer.ProducerDataSource
import com.example.beloo.foodnixtest.presentation.ActivityBindingsModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        GlobalModule::class,
        ActivityBindingsModule::class,
        AndroidSupportInjectionModule::class,
        NetworkApiModule::class,
        JsonConverterModule::class,
        RemoteDataSourceModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        RxModule::class
    ]
)
interface GlobalComponent {
    fun repositorySource(): ProducerDataSource

    fun inject(app: Application)
}
