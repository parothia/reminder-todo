package com.parothia.EmailVerification.db

import org.springframework.data.jpa.repository.JpaRepository

interface VerificationTokenRepository : JpaRepository<VerificationTokenEntity, Long> {

    fun findTokenByUserId(id: Long): VerificationTokenEntity

    fun findUserByToken(token: String): VerificationTokenEntity
}