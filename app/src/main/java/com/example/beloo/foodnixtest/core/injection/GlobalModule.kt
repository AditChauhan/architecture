package com.example.beloo.foodnixtest.core.injection

import android.content.ContentResolver
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources

import dagger.Module
import dagger.Provides

@Module
internal class GlobalModule(private val appContext: Context) {

    @Provides
    fun provideAppContext(): Context = appContext

    @Provides
    fun provideAssetsManager(context: Context): AssetManager = context.assets

    @Provides
    fun provideContentResolver(context: Context): ContentResolver = context.contentResolver

    @Provides
    fun provideResources(context: Context): Resources = context.resources

}
