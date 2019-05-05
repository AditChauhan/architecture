package com.example.beloo.foodnixtest.storage

import com.example.beloo.foodnixtest.storage.producer.ProducerDao
import com.example.beloo.foodnixtest.storage.producer.ProducerEntity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beloo.foodnixtest.storage.image.ImageEntity

@Database(
    entities = [
        ProducerEntity::class,
        ImageEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun producerDao(): ProducerDao
}