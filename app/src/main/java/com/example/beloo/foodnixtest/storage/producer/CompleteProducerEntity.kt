package com.example.beloo.foodnixtest.storage.producer

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.storage.ID_COLUMN
import com.example.beloo.foodnixtest.storage.image.ImageEntity
import com.example.beloo.foodnixtest.storage.image.ImageEntity.Companion.PRODUCER_ID_COLUMN

data class CompleteProducerEntity (
    @Embedded
    val producer: ProducerEntity,

    @Relation(
        parentColumn = ID_COLUMN,
        entity = ImageEntity::class,
        entityColumn = PRODUCER_ID_COLUMN
    )
    override val images: List<ImageEntity>

): Producer by producer