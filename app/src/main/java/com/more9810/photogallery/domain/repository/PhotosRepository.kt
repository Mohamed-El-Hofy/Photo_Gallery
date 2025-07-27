package com.more9810.photogallery.domain.repository

import com.more9810.photogallery.data.remote.api.state.Resource
import com.more9810.photogallery.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
     fun refreshPhotosFromApi(perPage: Int): Flow<Resource<List<Photo>>>
     fun getNetworkState(): Flow<Boolean>

}