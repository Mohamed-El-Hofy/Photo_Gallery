package com.more9810.photogallery.data.repository

import android.util.Log
import com.more9810.photogallery.common.mapper.toPhoto
import com.more9810.photogallery.common.mapper.toPhotoEntity
import com.more9810.photogallery.data.local.database.dao.PhotoDao
import com.more9810.photogallery.data.local.database.entity.PhotoEntity
import com.more9810.photogallery.data.local.systemData.NetworkChecker
import com.more9810.photogallery.data.remote.api.service.PexelsApiService
import com.more9810.photogallery.data.remote.api.state.Resource
import com.more9810.photogallery.domain.model.Photo
import com.more9810.photogallery.domain.repository.PhotosRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
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
            getNetworkState().collect { isConnect ->
                Log.e("TAG_Repo", "isConnect $isConnect", )
                try {
                    val response = apiService.getPhotos()
                    Log.e("TAG_Repo", "inside try", )

                    if (isConnect) {
                        Log.e("TAG_Repo", "inside is Connect $isConnect", )
                        if (response.isSuccessful) {
                            Log.e("TAG_Repo", "inside response successful", )

                            val photoDto =
                                response.body()?.photoDto?.mapNotNull { it?.toPhotoEntity() }.orEmpty()
                            updateAndSavDataInRoom(photoDto)
                        } else {
                            Log.e("TAG_Repo", "if response Exception", )
                            emit(Resource.Error(response.errorBody()?.string().toString()))
                            delay(5000)
                        }
                    } else {
                        Log.e("TAG_Repo", "inside else Connect Exception", )

                        emit(Resource.Error(response.errorBody()?.string().toString()))
                        delay(5000)
                    }
                }catch (e: Exception){
                    Log.e("TAG_Repo", "inside catch", )

                    emit(Resource.Error(e.toString()))
                    delay(5000)
                    val firstLocalPhoto = loadFromDB().map { Resource.Success(it) }.firstOrNull()
                    val localPhoto = loadFromDB().map { Resource.Success(it) }
                    if (!firstLocalPhoto?.data.isNullOrEmpty()) {
                        emit(firstLocalPhoto)
                        Log.e("TAG_Repo", "firstOrNull() ${!firstLocalPhoto?.data.isNullOrEmpty()}", )
                        emitAll(localPhoto)
                    }
                }
                Log.e("TAG_Repo", "outside is connect", )

                val firstLocalPhoto = loadFromDB().map { Resource.Success(it) }.firstOrNull()
                val localPhoto = loadFromDB().map { Resource.Success(it) }
                if (!firstLocalPhoto?.data.isNullOrEmpty()) {
                    emit(firstLocalPhoto)
                    Log.e("TAG_Repo", "firstOrNull() ${!firstLocalPhoto?.data.isNullOrEmpty()}", )
                    emitAll(localPhoto)
                }
            }
        }
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