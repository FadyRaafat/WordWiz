package com.fady.wordwiz.data.di

import android.content.Context
import com.fady.wordwiz.data.datasource.local.worddictionarydb.AppDataBase
import com.fady.wordwiz.data.datasource.local.worddictionarydb.WordDictionaryDao
import com.fady.wordwiz.data.datasource.remote.DictionaryRemoteDataSource
import com.fady.wordwiz.data.datasource.remote.service.ClientService
import com.fady.wordwiz.data.repository.DictionaryRepositoryImpl
import com.fady.wordwiz.domain.repository.DictionaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DictionaryModule {
    @Provides
    @Singleton
    fun provideDictionaryRepository(
        dictionaryRemoteDataSource: DictionaryRemoteDataSource, wordDictionaryDao: WordDictionaryDao
    ): DictionaryRepository {
        return DictionaryRepositoryImpl(dictionaryRemoteDataSource, wordDictionaryDao)
    }


    @Provides
    @Singleton
    fun provideDictionaryServices(retrofit: Retrofit): ClientService =
        retrofit.create(ClientService::class.java)

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AppDataBase = AppDataBase.getDatabase(context)


    @Singleton
    @Provides
    fun provideWordDictionaryDao(appDataBase: AppDataBase): WordDictionaryDao =
        appDataBase.getWordDictionaryDao()

}
