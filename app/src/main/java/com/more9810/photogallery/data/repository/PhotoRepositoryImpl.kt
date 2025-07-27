package com.more9810.photogallery.data.repository

import com.more9810.photogallery.common.mapper.toPhoto
import com.more9810.photogallery.common.mapper.toPhotoEntity
import com.more9810.photogallery.data.local.database.dao.PhotoDao
import com.more9810.photogallery.data.local.database.entity.PhotoEntity
import com.more9810.photogallery.data.local.systemData.NetworkChecker
import com.more9810.photogallery.data.remote.api.service.PexelsApiService
import com.more9810.photogallery.data.remote.api.state.Resource
import com.more9810.photogallery.domain.model.Photo
import com.more9810.photogallery.domain.repository.PhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val dao: PhotoDao,
    private val apiService: PexelsApiService,
    private val networkChecker: NetworkChecker,
) : PhotosRepository {

    override fun refreshPhotosFromApi(perPage: Int): Flow<Resource<List<Photo>>> {
        return flow {
            emit(Resource.Loading)
            networkChecker.observeNetwork().collect { isConnect->
                try {
                    if (isConnect) {
                        val response = apiService.getPhotos()

                        if (response.isSuccessful) {
                            val dtoList = response.body()?.photoDto.orEmpty()
                            val entity = dtoList.mapNotNull { it?.toPhotoEntity() }

                            if (entity.isNotEmpty()) {
                                updateAndSavDataInRoom(entity)
                            }

                            emitAll(
                                loadFromDB().map { Resource.Success(it) })

                        } else {
                            emit(
                                Resource.Error(
                                    "HTTP ${response.code()}" + "\n${response.message()}\n" + response.errorBody()
                                        ?.string()
                                        .toString() + "\n\nData, if available, will be fetched from the database after 5 seconds..."
                                )
                            )
                            delay(5000)
                            val roomData = loadFromDB().firstOrNull()
                            if (!roomData.isNullOrEmpty()) {
                                emit(Resource.Success(roomData))
                            }
                        }
                    } else {
                        emitAll(
                            loadFromDB().map { Resource.Success(it) }
                        )
                    }
                } catch (e: Exception) {
                    emit(
                        Resource.Error("${e}\n${e.message.toString()}\n\nData, if available, will be fetched from the database after 5 seconds...")
                    )
                    delay(5000)

                    val roomData = loadFromDB().firstOrNull()
                    if (!roomData.isNullOrEmpty()) {
                        emit(Resource.Success(roomData))
                    }
                }
            }

        }.flowOn(Dispatchers.IO)
    }



override fun getNetworkState(): Flow<Boolean> {
    return networkChecker.observeNetwork()
}

private suspend fun updateAndSavDataInRoom(list: List<PhotoEntity>) {
    dao.deleteAllPhotos()
    dao.insertAllPhotos(list)
}

private fun loadFromDB(): Flow<List<Photo>> {
    return dao.getAllPhotos().map { list ->
        list.map {
            it.toPhoto()
        }
    }
}


}