package com.parothia.EmailVerification.service

import com.parothia.EmailVerification.db.VerificationTokenEntity
import com.parothia.EmailVerification.dto.EmailVerificationResponseDTO
import com.parothia.usermanagement.dto.UserDTO
import org.springframework.stereotype.Service

@Service
interface EmailVerificationService {
    //    fun getVerificationToken(user: UserDTO): String
    fun verifyUserEmail(token: String): EmailVerificationResponseDTO
    fun generateVerificationToken(userId: Long): String
}