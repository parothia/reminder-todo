package com.parothia.usermanagement.service

import com.parothia.EmailVerification.service.EmailVerificationService
import com.parothia.mailer.db.EmailDetails
import com.parothia.mailer.service.EmailService
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

    @Autowired
    private lateinit var emailService: EmailService

    @Autowired
    private lateinit var emailVerificationService: EmailVerificationService


    internal fun sendVerificationMail(user: UserDTO) {
        val token = emailVerificationService.generateVerificationToken(user.id)
        val emailDetails = EmailDetails(
            user.email,
            "noreplay@parothia.in",
            "Verification Mail on SignIn",
            "This is a verification Mail from Parothia. Please click on below link to verify the authenticity of user ${token} ",
            emptyList()
        )
        emailService.sendSimpleEmail(emailDetails)
    }

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
            sendVerificationMail(userDTO)
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