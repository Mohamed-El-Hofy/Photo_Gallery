package com.more9810.photogallery.data.remote.api.pexelsResponse


import com.google.gson.annotations.SerializedName

data class PexelsPhotoResponse(
    @SerializedName("next_page")
    val nextPage: String? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("per_page")
    val perPage: Int? = null,
    @SerializedName("photos")
    val photoDto: List<PhotoDto?>? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)