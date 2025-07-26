package com.more9810.photogallery.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.more9810.photogallery.data.local.database.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhotos(photos: List<PhotoEntity>)

    @Query("delete from PhotoEntity")
    suspend fun deleteAllPhotos()

    @Query("select * from PhotoEntity")
    fun getAllPhotos(): Flow<List<PhotoEntity>>
}