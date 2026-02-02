package com.riteshapps.learnhub.data.repository

import com.riteshapps.learnhub.data.remote.api.AuthApi
import com.riteshapps.learnhub.data.remote.dto.LoginRequestDto
import com.riteshapps.learnhub.domain.model.User
import com.riteshapps.learnhub.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(
        username: String,
        password: String
    ): User {

        val response = api.login(
            LoginRequestDto(
                username = username,
                password = password,
                expiresInMins = 60
            )
        )

        return User(
            id = response.id,
            username = response.username,
            email = response.email,
            accessToken = response.token
        )
    }
}
