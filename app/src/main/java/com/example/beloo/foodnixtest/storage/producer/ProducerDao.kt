package com.example.beloo.foodnixtest.storage.producer

import androidx.room.*
import com.example.beloo.foodnixtest.storage.image.ImageEntity
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
interface ProducerDao {

    @Query("SELECT * FROM ProducerEntity")
    fun producersStream(): Observable<List<CompleteProducerEntity>>

    @Transaction
    fun put(items: List<CompleteProducerEntity>): Completable = Completable
        .fromAction {
            items
                .onEach {
                    put(it.producer)
                }
                .flatMap { it.images }
                .forEach { put(it) }
        }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun put(dialog: ImageEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun put(tag: ProducerEntity): Long
}