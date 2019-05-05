package com.example.beloo.foodnixtest.network.producer

import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class ProducersListPoJo(

    @field:JsonProperty("pagination")
	val pagination: PaginationPoJo,

    @field:JsonProperty("response")
	val producers: List<ProducerPoJo> = emptyList(),

    @field:JsonProperty("count")
	val count: Int
)

@Generated("com.robohorse.robopojogenerator")
data class ProducerPoJo(

    @field:JsonProperty("id")
    override val id: Int,

    @field:JsonProperty("name")
    override val name: String,

    @field:JsonProperty("description")
    override val description: String,

    @field:JsonProperty("short_description")
    override val shortDescription: String,

    @field:JsonProperty("images")
    override val images: List<ImagePoJo> = emptyList()

): Producer