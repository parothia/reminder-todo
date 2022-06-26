package com.parothia.usermanagement.dto

import com.parothia.shared.enum.UserType

data class VerifyOtpRequestDTO(
    val userId: Long,
    val userType: UserType,
    val otp: String,
    val password: String,
)
