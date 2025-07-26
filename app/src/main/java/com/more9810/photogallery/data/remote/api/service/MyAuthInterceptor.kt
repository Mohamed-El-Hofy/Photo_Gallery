package com.more9810.photogallery.data.remote.api.service

import com.more9810.photogallery.common.Const
import okhttp3.Interceptor
import okhttp3.Response

class MyAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request()
                .newBuilder()
                .addHeader("Authorization", Const.PEXELS_API_KEY)
                .build()
        return chain.proceed(request)
    }

}