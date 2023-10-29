package com.husqvarna.assignment.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.husqvarna.assignment.common.Constant
import com.husqvarna.assignment.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Repository
    @Provides
    @Singleton
    fun provideRepository(@Api api: com.husqvarna.assignment.data.remote.Api):
            com.husqvarna.assignment.domain.repository.Repository =
        RepositoryImpl(api)

    @Api
    @Provides
    @Singleton
    fun provideApi(@Retrofit retrofit: retrofit2.Retrofit): com.husqvarna.assignment.data.remote.Api =
        retrofit.create(com.husqvarna.assignment.data.remote.Api::class.java)

    @Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @OkHttp okHttp: OkHttpClient
    ): retrofit2.Retrofit =
        getRetrofit(Constant.BASE_URL, gsonConverterFactory, okHttp)

    @OkHttp
    @Singleton
    @Provides
    fun provideOkHttp(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor(mInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideInterceptorBuilder(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader(Constant.HEADER_CONTENT_TYPE, Constant.HEADER_CONTENT_TYPE_VALUE_JSON)
                .addHeader("Connection", "close")
            val request = requestBuilder.build()
            return@Interceptor chain.proceed(request)
        }
    }

    private fun getRetrofit(
        url: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttp: OkHttpClient
    ): retrofit2.Retrofit =
        retrofit2.Retrofit.Builder().baseUrl(url).addConverterFactory(gsonConverterFactory)
            .client(okHttp).build()

    private val mInterceptor = run {
        val httpLoggingInterceptor = LoggingIntercept()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = LoggingIntercept.Level.BODY
        }
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Repository

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Api

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Retrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OkHttp
}