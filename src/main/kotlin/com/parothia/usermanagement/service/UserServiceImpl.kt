package com.parothia.usermanagement.service

import com.parothia.usermanagement.UserManagementException
import com.parothia.usermanagement.db.UserEntity
import com.parothia.usermanagement.db.UserRepository
import com.parothia.usermanagement.dto.UserDTO
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun findOrCreateUser(userDTO: UserDTO): UserEntity {
        var user = userRepository.findByEmail(userDTO.email)
        if (user != null) {
            throw UserManagementException("User Already Exists")
        } else {
            user = UserEntity().apply {
                username = userDTO.username
                contact = userDTO.contact
                email = userDTO.email
                firstName = userDTO.firstName
                lastName = userDTO.lastName
            }
            userRepository.save(user)
            BeanUtils.copyProperties(user, userDTO)

        }
        return user
    }

    fun processUserRegistration(user: UserDTO): UserEntity {
        return findOrCreateUser(user)
    }

    override fun createUser(request: UserDTO) {
        request.let { dto -> BeanUtils.copyProperties(processUserRegistration(request), dto) }
    }
}