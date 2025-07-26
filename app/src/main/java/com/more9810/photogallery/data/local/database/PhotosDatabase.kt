package com.more9810.photogallery.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.more9810.photogallery.data.local.database.PhotosDatabase.Companion.DATABASE_LAST_VERSION
import com.more9810.photogallery.data.local.database.dao.PhotoDao
import com.more9810.photogallery.data.local.database.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version =DATABASE_LAST_VERSION )
abstract class PhotosDatabase: RoomDatabase() {
    abstract fun  getPhotoDao(): PhotoDao
    companion object{
        private const val DATABASE_LAST_VERSION = 1


    }

}