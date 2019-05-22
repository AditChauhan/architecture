package com.example.beloo.architecture.presentation

import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import dagger.Module
import dagger.Provides

@Module
class ActivityCommonModule {
    @Provides
    fun dexter(activity: PresentationActivity) : DexterBuilder.Permission = Dexter.withActivity(activity)

    @Provides
    fun subscriptionCache(cache: SubscriptionCacheImpl): SubscriptionCache = cache
}