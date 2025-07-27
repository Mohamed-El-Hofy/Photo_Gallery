package com.more9810.photogallery.domain.useCase

import com.more9810.photogallery.data.remote.api.state.Resource
import com.more9810.photogallery.domain.model.Photo
import com.more9810.photogallery.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDataUseCase @Inject constructor(
    private val repo: PhotosRepository
) {

    operator fun invoke(perPage: Int): Flow<Resource<List<Photo>>>{
        return repo.refreshPhotosFromApi(perPage)
    }
}