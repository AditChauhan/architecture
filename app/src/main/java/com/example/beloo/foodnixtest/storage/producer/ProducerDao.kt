package com.example.beloo.foodnixtest.storage.producer

import androidx.room.*
import com.example.beloo.foodnixtest.storage.image.ImageEntity
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
abstract class ProducerDao {

    @Transaction
    @Query("SELECT * FROM ProducerEntity")
    abstract fun producersStream(): Observable<List<CompleteProducerEntity>>

    fun putAsync(items: List<CompleteProducerEntity>): Completable = Completable
        .fromAction { put(items) }

    @Transaction
    open fun put(items: List<CompleteProducerEntity>) {
        items
            .onEach {
                put(it.producer)
            }
            .flatMap { it.images }
            .forEach { put(it) }
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun put(dialog: ImageEntity): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun put(tag: ProducerEntity): Int
}