package com.more9810.photogallery.data.repository

import android.util.Log
import com.more9810.photogallery.data.local.database.dao.PhotoDao
import com.more9810.photogallery.data.local.systemData.NetworkChecker
import com.more9810.photogallery.data.remote.api.pexelsResponse.PexelsPhotoResponse
import com.more9810.photogallery.data.remote.api.pexelsResponse.PhotoDto
import com.more9810.photogallery.data.remote.api.pexelsResponse.Src
import com.more9810.photogallery.data.remote.api.service.PexelsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val dao: PhotoDao,
    private val apiService: PexelsApiService,
    private val networkChecker: NetworkChecker
) {

    fun getPhoto(): Flow<Response<PexelsPhotoResponse<PhotoDto<Src>>>>{
        return flow {
            if (networkChecker.isConnected()){
                try {
                    val request = apiService.getPhotos()
                    if (request.isSuccessful){
                        Log.e("TAG_getPhoto", "getPhoto inRepo: ${request.body()?.photoDto}", )
                    }else{
                        Log.e("TAG_getPhoto", "getPhoto inRepo: ${request.errorBody()?.string()?.toString()}", )
                    }
                }catch (e: Exception){
                    Log.e("TAG_getPhoto", "getPhoto inRepo: ${e.message}", )
                }
            }else{
                Log.e("TAG_getPhoto", "getPhoto inRepo networkChecker: ${networkChecker.isConnected()}", )

            }
        }
    }

}