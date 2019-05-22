package com.example.beloo.architecture.network.producer

import com.example.beloo.architecture.data.model.image.Image
import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class ImagePoJo(

	@field:JsonProperty("path")
	override val path: String,

	@field:JsonProperty("position")
	override val position: Int
): Image