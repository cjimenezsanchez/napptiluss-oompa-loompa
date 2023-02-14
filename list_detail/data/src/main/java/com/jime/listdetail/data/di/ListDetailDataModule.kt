package com.jime.listdetail.data.di

import com.jime.listdetail.data.error.ErrorHandlerImpl
import com.jime.listdetail.data.remote.OompaLoompaApi
import com.jime.listdetail.data.repository.OompaLoompaRepositoryImp
import com.jime.listdetail.domain.error.ErrorHandler
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ListDetailDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    @Provides
    @Singleton
    fun provideOompaLoompaApi(client: OkHttpClient): OompaLoompaApi {
        return Retrofit.Builder()
            .baseUrl(OompaLoompaApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(OompaLoompaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOompaLoompaRepository(
        oompaLoompaApi: OompaLoompaApi,
        errorHandler: ErrorHandler
    ): OompaLoompaRepository {
        return OompaLoompaRepositoryImp(oompaLoompaApi, errorHandler)
    }

    @Provides
    @Singleton
    fun provideErrorHandler(): ErrorHandler {
        return ErrorHandlerImpl()
    }
}
