package com.more9810.photogallery.data.remote.api.state

sealed class Resource<out T>(val data: T?= null,val msg : String?="") {
    class Success<T>(data: T) : Resource<T>(data)
     class Error<T>( msg: String?) : Resource<T>(msg =msg )
    object Loading : Resource<Nothing>(null)
    class NetworkChecker(isConnect: Boolean?): Resource<Nothing>(null)

}