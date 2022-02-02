package com.yeyint.hiltexample.data.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yeyint.hiltexample.data.remote.AuthInspector
import com.yeyint.hiltexample.data.remote.PostService
import com.yeyint.hiltexample.utils.Constant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PostRetrofit

    @Singleton
    @Provides
    fun provideHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(AuthInspector(appContext))
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGson() : Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    @PostRetrofit
    fun providePostRetrofit(
        gson: Gson,
        @ApplicationContext context: Context
    ) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideHttpClient(context))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun providePostService(@PostRetrofit retrofit: Retrofit) : PostService =
        retrofit.create(PostService::class.java)
}