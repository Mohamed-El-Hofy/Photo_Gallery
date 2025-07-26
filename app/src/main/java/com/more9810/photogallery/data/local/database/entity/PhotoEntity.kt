package com.more9810.photogallery.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
    @PrimaryKey
    val id : String,
    val originalPhoto: String,
)
