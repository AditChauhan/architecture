package com.example.beloo.foodnixtest

import android.app.Activity
import android.app.Application

import com.example.beloo.foodnixtest.core.injection.ComponentCreator
import com.example.beloo.foodnixtest.core.injection.ComponentFactory
import com.example.beloo.foodnixtest.core.injection.ComponentProvider
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class FoodApp : Application(), HasActivityInjector {

	@Inject
	internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

	override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

	private lateinit var componentFactory: ComponentFactory

	override fun onCreate() {
		super.onCreate()

		if (BuildConfig.DEBUG) {
			Stetho.initializeWithDefaults(this)
		}

		instance = this
		componentFactory = ComponentProvider(ComponentCreator(this))
		componentFactory().globalComponent().inject(this)
	}

	fun componentFactory(): ComponentFactory = componentFactory

	companion object {
		private lateinit var instance: com.example.beloo.foodnixtest.FoodApp
		fun instance(): com.example.beloo.foodnixtest.FoodApp = instance
	}
}
