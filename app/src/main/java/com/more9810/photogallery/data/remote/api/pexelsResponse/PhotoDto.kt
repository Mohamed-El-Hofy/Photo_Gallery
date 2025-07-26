package com.more9810.photogallery.data.remote.api.pexelsResponse


import com.google.gson.annotations.SerializedName


data class PhotoDto<T>(
    @SerializedName("alt")
    val alt: String? = null,
    @SerializedName("avg_color")
    val avgColor: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("liked")
    val liked: Boolean? = null,
    @SerializedName("photographer")
    val photographer: String? = null,
    @SerializedName("photographer_id")
    val photographerId: Long? = null,
    @SerializedName("photographer_url")
    val photographerUrl: String? = null,
    @SerializedName("src")
    val src: T? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("width")
    val width: Int? = null
)