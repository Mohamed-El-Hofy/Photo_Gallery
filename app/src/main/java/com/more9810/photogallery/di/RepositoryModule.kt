package com.more9810.photogallery.di

import android.content.Context
import com.more9810.photogallery.data.local.database.dao.PhotoDao
import com.more9810.photogallery.data.local.systemData.NetworkChecker
import com.more9810.photogallery.data.remote.api.service.PexelsApiService
import com.more9810.photogallery.data.repository.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNetworkChecker(@ApplicationContext context: Context): NetworkChecker {
        return NetworkChecker(context)
    }

    @Provides
    @Singleton
    fun provideRepository(
        dao: PhotoDao,
        apiService: PexelsApiService,
        networkChecker: NetworkChecker

    ): PhotoRepositoryImpl {

        return PhotoRepositoryImpl(
            dao = dao,
            apiService = apiService,
            networkChecker = networkChecker
        )
    }

}