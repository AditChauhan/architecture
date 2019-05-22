package com.example.beloo.architecture.data.model.producer

import com.example.beloo.architecture.data.model.image.Image

/** producer implementation should implement [Any.equals] to work with recycler adapters */
interface Producer {
    val id: Int

    val name: String

    val description: String

    val shortDescription: String

    val images: List<Image>
}

val Producer.firstImage: Image?
    get() = images.firstOrNull()