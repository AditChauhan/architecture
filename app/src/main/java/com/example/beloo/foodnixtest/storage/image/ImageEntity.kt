package com.example.beloo.foodnixtest.storage.image

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.beloo.foodnixtest.data.model.image.Image
import com.example.beloo.foodnixtest.storage.ID_COLUMN
import com.example.beloo.foodnixtest.storage.image.ImageEntity.Companion.PATH_COLUMN
import com.example.beloo.foodnixtest.storage.image.ImageEntity.Companion.PRODUCER_ID_COLUMN
import com.example.beloo.foodnixtest.storage.producer.ProducerEntity

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ProducerEntity::class,
            parentColumns = [ID_COLUMN],
            childColumns = [PRODUCER_ID_COLUMN],
            onDelete = ForeignKey.CASCADE
        )],
    primaryKeys = [PRODUCER_ID_COLUMN, PATH_COLUMN]
)
data class ImageEntity(
    @ColumnInfo(name = PATH_COLUMN)
    override val path: String,

    override val position: Int,

    @ColumnInfo(name = PRODUCER_ID_COLUMN)
    val producerId: Int
) : Image {

    companion object {
        const val PRODUCER_ID_COLUMN = "producer_id"
        const val PATH_COLUMN = "path"
    }
}