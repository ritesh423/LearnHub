package com.riteshapps.learnhub.domain.usecases

import com.riteshapps.learnhub.domain.model.User
import com.riteshapps.learnhub.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String
    ): User {
        return repository.login(username, password)
    }
}
