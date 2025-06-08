package com.appgim.data.api.interceptor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ResponseInterceptor @Inject constructor() : Interceptor {
    private val _isUserAuthorized = MutableStateFlow(true)
    val isUserAuthorized: StateFlow<Boolean> = _isUserAuthorized.asStateFlow()

    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request().newBuilder().build())
        if (response.code == 401) { // Unauthorized
            _isUserAuthorized.value = false
        }
        return response
    }
}