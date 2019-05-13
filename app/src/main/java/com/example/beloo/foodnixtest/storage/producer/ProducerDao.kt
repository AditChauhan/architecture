package com.example.beloo.foodnixtest.storage.producer

import androidx.room.*
import com.example.beloo.foodnixtest.storage.image.ImageEntity
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
abstract class ProducerDao {

    @Transaction
    @Query("SELECT * FROM ProducerEntity ORDER BY name")
    abstract fun producersStream(): Observable<List<CompleteProducerEntity>>

    fun put(items: List<CompleteProducerEntity>): Completable = Completable
        .fromAction { items.forEach { put(it) } }

    @Transaction
    protected open fun put(item: CompleteProducerEntity) {
        put(item.producer)
        item.images.forEach { put(it) }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun put(dialog: ImageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun put(tag: ProducerEntity)
}