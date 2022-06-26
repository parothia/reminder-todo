package com.parothia.EmailVerification.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface VerificationTokenRepository : JpaRepository<VerificationTokenEntity, Long> {

    fun findTokenByUserId(id: Long): String
    fun findUserByToken(token: String): VerificationTokenEntity

    @Modifying
    @Query("delete from VerificationTokenEntity vt where vt.user.id=:userID")
    fun deleteTokenByUserId(userID: Long)
}