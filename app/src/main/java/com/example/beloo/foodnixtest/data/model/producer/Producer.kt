package com.example.beloo.foodnixtest.data.model.producer

import com.example.beloo.foodnixtest.data.model.image.Image

interface Producer {
    val id: Int

    val name: String

    val description: String

    val shortDescription: String

    val images: List<Image>
}

val Producer.firstImage: Image?
    get() = images.firstOrNull()
