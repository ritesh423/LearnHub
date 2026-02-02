package com.riteshapps.learnhub.data.remote.api

import com.riteshapps.learnhub.data.remote.dto.LoginRequestDto
import com.riteshapps.learnhub.data.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("user/login")
    suspend fun login(
        @Body loginRequest: LoginRequestDto
    ): LoginResponseDto
}
