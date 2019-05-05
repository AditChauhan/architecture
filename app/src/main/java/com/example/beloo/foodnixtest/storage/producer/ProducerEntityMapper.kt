package com.example.beloo.foodnixtest.storage.producer

import com.example.beloo.foodnixtest.data.model.image.Image
import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.storage.image.ImageEntity
import javax.inject.Inject

interface ProducerEntityMapper {
    fun map(producer: Producer): CompleteProducerEntity
}

internal class ProducerEntityMapperImpl @Inject constructor() : ProducerEntityMapper {

    private fun map(image: Image, producerId: Int): ImageEntity = ImageEntity(
        path = image.path,
        position = image.position,
        producerId = producerId
    )

    override fun map(producer: Producer): CompleteProducerEntity = CompleteProducerEntity(
        producer = ProducerEntity(
            id = producer.id,
            name = producer.name,
            description = producer.description,
            shortDescription = producer.shortDescription
        ),
        images = producer.images.map { map(it, producerId = producer.id) }
    )

}
