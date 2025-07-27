package com.more9810.photogallery.di

import android.content.Context
import com.more9810.photogallery.data.local.database.dao.PhotoDao
import com.more9810.photogallery.data.local.systemData.NetworkChecker
import com.more9810.photogallery.data.remote.api.service.PexelsApiService
import com.more9810.photogallery.data.repository.PhotoRepositoryImpl
import com.more9810.photogallery.data.repository.ThemeRepositoryImpl
import com.more9810.photogallery.domain.repository.PhotosRepository
import com.more9810.photogallery.domain.repository.ThemeRepository
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
    fun provideThemRepository(@ApplicationContext context: Context): ThemeRepository {
        return ThemeRepositoryImpl(context)
    }
    @Provides
    @Singleton
    fun provideRepository(
        dao: PhotoDao,
        apiService: PexelsApiService,
        networkChecker: NetworkChecker

    ): PhotosRepository {

        return PhotoRepositoryImpl(
            dao = dao,
            apiService = apiService,
            networkChecker = networkChecker
        )
    }

}