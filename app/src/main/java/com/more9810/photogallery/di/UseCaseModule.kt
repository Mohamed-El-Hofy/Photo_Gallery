package com.more9810.photogallery.di

import com.more9810.photogallery.domain.repository.PhotosRepository
import com.more9810.photogallery.domain.repository.ThemeRepository
import com.more9810.photogallery.domain.useCase.FetchDataUseCase
import com.more9810.photogallery.domain.useCase.GetNetworkStateUseCase
import com.more9810.photogallery.domain.useCase.GetThemeModeUseCase
import com.more9810.photogallery.domain.useCase.ToggleThemeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideNetworkUseCase(repository: PhotosRepository) = GetNetworkStateUseCase(repository)
    @Provides
    fun provideDataUseCase(repository: PhotosRepository) = FetchDataUseCase(repository)

    @Provides
    fun provideGetThemeModeUseCase(repository: ThemeRepository) = GetThemeModeUseCase(repository)

    @Provides
    fun provideToggleThemeUseCase(repository: ThemeRepository) = ToggleThemeUseCase(repository)

}
