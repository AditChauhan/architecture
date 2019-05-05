package com.example.beloo.foodnixtest.storage.producer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beloo.foodnixtest.data.model.image.Image
import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.storage.ID_COLUMN
import java.lang.UnsupportedOperationException

@Entity
data class ProducerEntity (
    @PrimaryKey
    @ColumnInfo(name = ID_COLUMN)
    override val id: Int,

    override val name: String,

    override val description: String,

    override val shortDescription: String
): Producer {
    override val images: List<Image>
        get() = throw UnsupportedOperationException("this relation should be resolved in dao")
}