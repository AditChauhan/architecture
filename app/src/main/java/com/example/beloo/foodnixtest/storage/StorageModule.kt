package com.example.beloo.foodnixtest.storage

import android.content.Context
import androidx.room.Room
import com.example.beloo.foodnixtest.FoodApp
import com.example.beloo.foodnixtest.storage.producer.ProducerDao
import com.example.beloo.foodnixtest.storage.producer.ProducerEntityMapper
import com.example.beloo.foodnixtest.storage.producer.ProducerEntityMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DaoModule::class,
        EntityMappersModule::class
    ]
)
interface StorageModule

@Module
internal class DaoModule {

    @Singleton
    @Provides
    fun database(context: Context): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, "database")
        .build()

    @Singleton
    @Provides
    fun producerDao(database: AppDatabase): ProducerDao = database.producerDao()
}

@Module
internal interface EntityMappersModule {

    @Binds
    fun provideProducerMapper(mapper: ProducerEntityMapperImpl): ProducerEntityMapper

}