package hcm.nnbinh.cryptowallet.di

import hcm.nnbinh.cryptowallet.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT_DURATION = 30L

val retrofitModule = module {
	single { Cache(androidContext().cacheDir, 10L * 1024 * 1024) }
	single { GsonBuilder().create() }
	single { retrofitHttpClient() }
	single { retrofitBuilder() }
	single {
		Interceptor { chain ->
			chain.proceed(chain.request().newBuilder().apply {
				header("Accept", "application/json")
			}.build())
		}
	}
}

private fun Scope.retrofitBuilder(): Retrofit {
	return Retrofit.Builder()
		.baseUrl(BuildConfig.BASE_URL)
		.addConverterFactory(GsonConverterFactory.create(get()))
		.client(get())
		.build()
}


private fun Scope.retrofitHttpClient(): OkHttpClient {
	return OkHttpClient.Builder().apply {
		cache(get())
		connectTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
		writeTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
		readTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
		retryOnConnectionFailure(true)
		addInterceptor(HttpLoggingInterceptor().apply {
			level = if (BuildConfig.DEBUG) {
				HttpLoggingInterceptor.Level.HEADERS
			}
			else {
				HttpLoggingInterceptor.Level.NONE
			}
		})
	}.build()
}