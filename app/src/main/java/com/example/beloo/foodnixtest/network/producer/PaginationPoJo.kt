package com.example.beloo.foodnixtest.network.producer

import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class PaginationPoJo(

	@field:JsonProperty("next")
	val next: Int? = null,

	@field:JsonProperty("per_page")
	val perPage: Int,

	@field:JsonProperty("current")
	val current: Int,

	@field:JsonProperty("pages")
	val pages: Int,

	@field:JsonProperty("previous")
	val previous: Any? = null,

	@field:JsonProperty("count")
	val count: Int
)