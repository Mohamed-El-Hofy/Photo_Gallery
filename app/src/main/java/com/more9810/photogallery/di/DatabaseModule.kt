package com.more9810.photogallery.di

import android.content.Context
import androidx.room.Room
import com.more9810.photogallery.data.local.database.PhotosDatabase
import com.more9810.photogallery.data.local.database.dao.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): PhotosDatabase {
        val dbName = "photoDatabase.db"
        return Room.databaseBuilder(context, PhotosDatabase::class.java, dbName).build()
    }

    @Provides
    @Singleton
    fun providePhotoDao(
        db: PhotosDatabase,
    ): PhotoDao {
        return db.getPhotoDao()
    }


}