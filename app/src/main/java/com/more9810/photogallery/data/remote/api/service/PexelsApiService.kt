package com.more9810.photogallery.data.remote.api.service

import com.more9810.photogallery.common.Const
import com.more9810.photogallery.data.remote.api.pexelsResponse.PexelsPhotoResponse
import com.more9810.photogallery.data.remote.api.pexelsResponse.PhotoDto
import com.more9810.photogallery.data.remote.api.pexelsResponse.Src
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApiService {
    @GET(Const.END_POINT)
    suspend fun getPhotos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): Response<PexelsPhotoResponse<PhotoDto<Src>>>
}