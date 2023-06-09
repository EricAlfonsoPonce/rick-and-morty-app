package com.ericalfonsoponce.rick_and_morty_app.di


import com.ericalfonsoponce.rick_and_morty_app.BuildConfig
import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.api.CharactersApi
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): LoggingInterceptor =
        LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BODY)
            .request("Request")
            .response("Response")
            .addHeader("Content-Type", "application/json")
            .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: LoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): CharactersApi =
        retrofit.create(CharactersApi::class.java)

}