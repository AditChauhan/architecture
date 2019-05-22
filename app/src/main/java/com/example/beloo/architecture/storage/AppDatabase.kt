package com.example.beloo.architecture.storage

import com.example.beloo.architecture.storage.producer.ProducerDao
import com.example.beloo.architecture.storage.producer.ProducerEntity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beloo.architecture.storage.image.ImageEntity

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