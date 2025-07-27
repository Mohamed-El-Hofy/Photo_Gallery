package com.more9810.photogallery.domain.useCase

import com.more9810.photogallery.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNetworkStateUseCase @Inject constructor(
    private val repo: PhotosRepository,
) {

    operator fun invoke(): Flow<Boolean> = repo.getNetworkState()
}