package com.jime.listdetail.data.di

import android.app.Application
import androidx.room.Room
import com.jime.listdetail.data.error.ErrorHandlerImpl
import com.jime.listdetail.data.local.OompaLoompaDao
import com.jime.listdetail.data.local.OompaLoompaDatabase
import com.jime.listdetail.data.remote.OompaLoompaApi
import com.jime.listdetail.data.repository.OompaLoompaRepositoryImp
import com.jime.listdetail.domain.error.ErrorHandler
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideDatabase(application: Application): OompaLoompaDatabase {
        return Room.databaseBuilder(
            application,
            OompaLoompaDatabase::class.java,
            "oompaLoompa"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: OompaLoompaDatabase): OompaLoompaDao {
        return database.dao
    }

    @Provides
    @Singleton
    fun provideOompaLoompaRepository(
        oompaLoompaApi: OompaLoompaApi,
        oompaLoompaDao: OompaLoompaDao,
        errorHandler: ErrorHandler
    ): OompaLoompaRepository {
        return OompaLoompaRepositoryImp(oompaLoompaApi, oompaLoompaDao, errorHandler)
    }

    @Provides
    @Singleton
    fun provideErrorHandler(): ErrorHandler {
        return ErrorHandlerImpl()
    }
}
