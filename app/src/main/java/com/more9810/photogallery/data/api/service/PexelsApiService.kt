package com.more9810.photogallery.data.api.service

import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApiService {
    @GET("curated")
    suspend fun getPhotos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    )
}