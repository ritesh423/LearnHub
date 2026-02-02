package com.riteshapps.learnhub.data.remote.dto

data class LoginResponseDto(
    val id: Int,
    val username: String,
    val email: String,
    val token: String
)

