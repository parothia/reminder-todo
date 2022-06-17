package com.parothia.EmailVerification.service

import com.parothia.EmailVerification.EmailVerificationException
import com.parothia.EmailVerification.db.VerificationTokenRepository
import com.parothia.EmailVerification.db.VerificationTokenEntity
import com.parothia.EmailVerification.dto.EmailVerificationResponseDTO
import com.parothia.shared.enum.UserActivationStatus
import com.parothia.usermanagement.db.UserRepository
import com.parothia.usermanagement.dto.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.*

@Service
class EmailVerificationServiceImpl : EmailVerificationService {

    @Autowired
    private lateinit var verifyTokenRepository: VerificationTokenRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun generateVerificationToken(userId: Long): String {
        val emailToken: String = UUID.randomUUID().toString()

        var userEntity = userRepository.findById(userId)
        if (!userEntity.isPresent) {
            throw EmailVerificationException("User Not Found")
        }
        var user = userEntity.get()
        val token = VerificationTokenEntity(emailToken, user)
        verifyTokenRepository.save(token)
        user.status = UserActivationStatus.INACTIVE
        userRepository.save(user)
        return emailToken
    }

    override fun getVerificationToken(user: UserDTO): VerificationTokenEntity {
        return verifyTokenRepository.findTokenByUserId(user.id)
    }

    override fun verifyUserEmail(token: String): EmailVerificationResponseDTO {
        val verificationToken = verifyTokenRepository.findUserByToken(token)
        verificationToken.let {
            var user = it.user
            if (user.status == UserActivationStatus.ACTIVE) {
                return EmailVerificationResponseDTO("Email already verified")
            }

            if (verificationToken.expiry.isAfter(ZonedDateTime.now())) {
                user.status = UserActivationStatus.EMAIL_VERIFIED
                userRepository.save(user)
                return EmailVerificationResponseDTO("User successfully verified")
            }
            return EmailVerificationResponseDTO("Verify token Expired")
        }
    }
}