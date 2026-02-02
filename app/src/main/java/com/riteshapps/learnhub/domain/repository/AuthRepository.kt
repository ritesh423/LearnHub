package com.riteshapps.learnhub.domain.repository

import com.riteshapps.learnhub.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): User
}
