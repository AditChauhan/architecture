package com.example.beloo.architecture.core.injection


import com.example.beloo.architecture.FoodApp
import com.example.beloo.architecture.core.rx.RxModule
import com.example.beloo.architecture.data.repository.RepositoryModule
import com.example.beloo.architecture.domain.UseCaseModule
import com.example.beloo.architecture.network.JsonConverterModule
import com.example.beloo.architecture.network.NetworkApiModule
import com.example.beloo.architecture.network.RemoteDataSourceModule
import com.example.beloo.architecture.presentation.ActivityBindingsModule
import com.example.beloo.architecture.storage.StorageModule
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
        StorageModule::class,
        UseCaseModule::class,
        RxModule::class
    ]
)
interface GlobalComponent {
    fun inject(app: FoodApp)
}
