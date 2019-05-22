package com.example.beloo.architecture.network

import android.content.Context
import android.net.Uri
import com.example.beloo.architecture.BuildConfig
import com.example.beloo.architecture.network.producer.ProducersApi
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [JsonConverterModule::class])
class NetworkApiModule {

	@Provides
	internal fun provideHttpClient(context: Context): OkHttpClient {
		// Enable caching for OkHttp
		val cacheSize = 10 * 1024 * 1024 // 10 MiB
		val cache = Cache(context.cacheDir, cacheSize.toLong())

		val builder = OkHttpClient().newBuilder()
			.cache(cache)
			.connectTimeout(30, TimeUnit.SECONDS)

		builder.addInterceptor { chain ->
			val request = chain.request().newBuilder()
				.addHeader(HTTP.Headers.CONTENT_TYPE, HTTP.MimeType.APPLICATION_JSON)
				.addHeader(HTTP.Headers.ACCEPT, HTTP.MimeType.APPLICATION_JSON)
				.addHeader(HTTP.Headers.HOST, Uri.parse(BuildConfig.FARMDROP_HOST).host!!)
				.addHeader(HTTP.Headers.ACCEPT_LANGUAGE, "en-GB")
				.build()
			chain.proceed(request)
		}

		//logging interceptor should be last interceptor in chain
		if (BuildConfig.DEBUG) {
			val loggingInterceptor = HttpLoggingInterceptor()
			loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

			builder.addInterceptor(loggingInterceptor)
				.addNetworkInterceptor(StethoInterceptor())
		}

		val cookieManager = CookieManager()
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
		builder.cookieJar(JavaNetCookieJar(cookieManager))

		return builder.build()
	}

	@Singleton
	@Provides
	internal fun provideRetrofitAdapter(client: OkHttpClient, factory: Factory): Retrofit =
		Retrofit.Builder()
			.baseUrl(BuildConfig.FARMDROP_HOST)
			.client(client)
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.addConverterFactory(factory)
			.build()

	@Provides
	internal fun provideProducersApi(retrofit: Retrofit): ProducersApi =
		retrofit.create(ProducersApi::class.java)

}
