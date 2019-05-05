package com.example.beloo.foodnixtest.core.rx

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.RxThreadFactory
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

@Module
class RxModule {

    @Provides
    @RxMainThread
    internal fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @RxNetwork
    internal fun provideNetworkScheduler(): Scheduler =
            Schedulers.from(Executors.newFixedThreadPool(5, RxThreadFactory("WRxNetworkScheduler-")))

    @Provides
    @RxIO
    internal fun provideIOScheduler(): Scheduler = Schedulers.io()
}
