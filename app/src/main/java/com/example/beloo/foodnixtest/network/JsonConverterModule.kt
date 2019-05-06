package com.example.beloo.foodnixtest.network

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
class JsonConverterModule {

	@Provides
	internal fun provideObjectMapper(): ObjectMapper {
		val module = SimpleModule("", Version.unknownVersion())

		//jackson object mapper setup
		return ObjectMapper()
			.registerModule(module)
			.registerKotlinModule()
			.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
	}

	@Provides
	internal fun provideJacksonConverterFactory(objectMapper: ObjectMapper): Converter.Factory =
		JacksonConverterFactory.create(objectMapper)

}
