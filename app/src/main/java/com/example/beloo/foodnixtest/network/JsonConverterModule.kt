package com.example.beloo.foodnixtest.network

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule

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
		val objectMapper = ObjectMapper()
		objectMapper.registerModule(module)
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

		return objectMapper
	}

	@Provides
	internal fun provideJacksonConverterFactory(objectMapper: ObjectMapper): Converter.Factory {
		return JacksonConverterFactory.create(objectMapper)
	}

}
