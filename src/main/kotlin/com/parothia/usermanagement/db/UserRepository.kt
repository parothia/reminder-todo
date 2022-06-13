package com.parothia.usermanagement.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.Nullable
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    @Nullable
    fun findByEmail(email: String): UserEntity
}