package com.example.beloo.foodnixtest.presentation

import com.example.beloo.foodnixtest.presentation.producers.ProducersActivity
import com.example.beloo.foodnixtest.core.injection.ActivityScope
import com.example.beloo.foodnixtest.presentation.producers.RestaurantsPresentationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [(AndroidSupportInjectionModule::class)])
interface ActivityBindingsModule {
	@ActivityScope
	@ContributesAndroidInjector(modules = [RestaurantsPresentationModule::class])
	fun restaurantsActivity(): ProducersActivity
}