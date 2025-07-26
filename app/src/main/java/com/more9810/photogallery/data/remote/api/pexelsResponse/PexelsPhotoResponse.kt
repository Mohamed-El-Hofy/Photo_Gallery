package com.more9810.photogallery.data.remote.api.pexelsResponse


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PexelsPhotoResponse<T>(
    @SerializedName("next_page")
    val nextPage: String? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("per_page")
    val perPage: Int? = null,
    @SerializedName("photos")
    val photoDto: List<T?>? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)