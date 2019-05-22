package com.example.beloo.architecture.presentation.producers

import com.example.beloo.architecture.presentation.ActivityCommonModule
import com.example.beloo.architecture.presentation.BasePresenter
import com.example.beloo.architecture.presentation.BaseView
import com.example.beloo.architecture.presentation.PresentationActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [
    ActivityCommonModule::class,
    RestaurantsPresentationModule.ProvideModule::class])
interface RestaurantsPresentationModule {

    @Binds
    fun provideActivity(activity: ProducersActivity): PresentationActivity

    @Binds
    fun provideView(activity: ProducersActivity): ProducersContract.View

    @Binds
    fun presenter(presenter: ProducersPresenter): ProducersContract.Presenter

    @Module
    class ProvideModule {

        @Provides
        @JvmSuppressWildcards
        fun basePresenter(presenter: ProducersContract.Presenter): BasePresenter<BaseView> = presenter as BasePresenter<BaseView>
    }
}
