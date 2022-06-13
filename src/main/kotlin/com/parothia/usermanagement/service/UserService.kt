package com.parothia.usermanagement.service

import com.parothia.usermanagement.db.UserRepository
import com.parothia.usermanagement.dto.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun createUser(request: UserDTO)

}