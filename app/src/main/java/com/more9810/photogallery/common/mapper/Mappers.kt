package com.more9810.photogallery.common.mapper

import com.more9810.photogallery.data.local.database.entity.PhotoEntity
import com.more9810.photogallery.data.remote.api.pexelsResponse.PhotoDto
import com.more9810.photogallery.domain.model.Photo


fun PhotoDto.toPhotoEntity(): PhotoEntity {
    return PhotoEntity(
        id = this.id.toString(),
        originalPhoto = this.src?.medium.toString()
    )

}
fun PhotoDto.toPhoto(): Photo {
    return Photo(
        id = this.id.toString(),
        originalPhoto = this.src?.medium.toString()
    )

}


fun PhotoEntity.toPhoto(): Photo {
    return Photo(
        id = this.id,
        originalPhoto = this.originalPhoto
    )

}


