package com.more9810.photogallery.di

import android.util.Log
import com.more9810.photogallery.common.Const
import com.more9810.photogallery.data.remote.api.service.MyAuthInterceptor
import com.more9810.photogallery.data.remote.api.service.PexelsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(): MyAuthInterceptor {
        return MyAuthInterceptor()
    }


    @Singleton
    @Provides
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { loggingText ->
            Log.e(Const.LOGGING_INTERCEPTOR_TAG, loggingText)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Singleton
    @Provides
    fun provideOkhttpClint(
        authInterceptor: MyAuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        clint: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,

        ): Retrofit {
        return Retrofit.Builder().baseUrl(Const.BASE_URL).client(clint)
            .addConverterFactory(gsonConverterFactory).build()
    }


    @Singleton
    @Provides
    fun providePexelsService(
        retrofit: Retrofit,
    ): PexelsApiService {
        return retrofit.create(PexelsApiService::class.java)
    }


}