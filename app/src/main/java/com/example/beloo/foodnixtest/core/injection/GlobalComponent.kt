package com.example.beloo.foodnixtest.core.injection


import com.example.beloo.foodnixtest.FoodApp
import com.example.beloo.foodnixtest.core.rx.RxModule
import com.example.beloo.foodnixtest.data.repository.RepositoryModule
import com.example.beloo.foodnixtest.domain.UseCaseModule
import com.example.beloo.foodnixtest.network.JsonConverterModule
import com.example.beloo.foodnixtest.network.NetworkApiModule
import com.example.beloo.foodnixtest.network.RemoteDataSourceModule
import com.example.beloo.foodnixtest.presentation.ActivityBindingsModule
import com.example.beloo.foodnixtest.storage.StorageModule
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
