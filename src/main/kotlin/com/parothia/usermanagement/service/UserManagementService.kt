package com.parothia.usermanagement.service

import com.parothia.usermanagement.db.UserEntity
import com.parothia.usermanagement.dto.UserDTO
import com.parothia.usermanagement.dto.VerifyOtpRequestDTO
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
interface UserManagementService : UserDetailsService {
    fun createUser(request: UserDTO)
    fun getAllUsers(): List<UserEntity>
    fun getUserByEmail(email: String): UserDTO
    fun updatePassword()
    fun verifyOtpAndSetPassword(verifyOtpRequestDTO: VerifyOtpRequestDTO)


}