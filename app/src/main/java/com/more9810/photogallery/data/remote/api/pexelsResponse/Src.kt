package com.more9810.photogallery.data.remote.api.pexelsResponse


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Src(
    @SerializedName("landscape")
    val landscape: String? = null,
    @SerializedName("large")
    val large: String? = null,
    @SerializedName("large2x")
    val large2x: String? = null,
    @SerializedName("medium")
    val medium: String? = null,
    @SerializedName("original")
    val original: String? = null,
    @SerializedName("portrait")
    val portrait: String? = null,
    @SerializedName("small")
    val small: String? = null,
    @SerializedName("tiny")
    val tiny: String? = null
) : Parcelable