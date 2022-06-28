package com.parothia.usermanagement.service

import com.parothia.EmailVerification.db.VerificationTokenRepository
import com.parothia.EmailVerification.service.EmailVerificationService
import com.parothia.mailer.db.EmailDetails
import com.parothia.mailer.service.EmailService
import com.parothia.security.MyUserDetails
import com.parothia.shared.enum.UserActivationStatus
import com.parothia.shared.enum.UserType
import com.parothia.usermanagement.UserManagementException
import com.parothia.usermanagement.db.UserEntity
import com.parothia.usermanagement.db.UserRepository
import com.parothia.usermanagement.dto.UserDTO
import com.parothia.usermanagement.dto.VerifyOtpRequestDTO
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserManagementServiceImpl : UserManagementService {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var emailService: EmailService

    @Autowired
    private lateinit var emailVerificationService: EmailVerificationService

    @Autowired
    private lateinit var tokenRepository: VerificationTokenRepository

    @Autowired
    private lateinit var passwordEncoder: BCryptPasswordEncoder


    internal fun sendVerificationMail(user: UserDTO) {
        val token = user.id?.let { emailVerificationService.generateVerificationToken(it) }
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
                id = userDTO.id
                username = userDTO.username.toString()
                contact = userDTO.contact.toString()
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

    override fun getAllUsers(): List<UserEntity> {
        return userRepository.findAll()
    }

    override fun getUserByEmail(email: String): UserDTO {
        val userEntity = userRepository.findByEmail(email)
        var userDTO: UserDTO = UserDTO()
        BeanUtils.copyProperties(userEntity, userDTO)
        return userDTO
    }

    override fun updatePassword() {
        TODO("Not yet implemented")
    }

    override fun verifyOtpAndSetPassword(verifyOtpRequestDTO: VerifyOtpRequestDTO) {
        var user: UserEntity = userRepository.getOne(verifyOtpRequestDTO.userId)
        try {
            user.status = UserActivationStatus.ACTIVE
            user.password = passwordEncoder.encode(verifyOtpRequestDTO.password)
            user = userRepository.save(user)
            user.id?.let { tokenRepository.deleteTokenByUserId(it) }

        } catch (ex: Exception) {
            throw UserManagementException("Daya kuch to locha hai...")
        }
    }

    override fun loadUserByUsername(username: String): UserDetails {
//        val userEntity = userRepository.findByEmail(username)
//        userEntity.let {
        return MyUserDetails(
            username,
            BCryptPasswordEncoder().encode("pass"),
            true,
            true,
            true,
            true,
            setOf<GrantedAuthority>(SimpleGrantedAuthority("ROLE_USER"))
        )
//        }
    }
}