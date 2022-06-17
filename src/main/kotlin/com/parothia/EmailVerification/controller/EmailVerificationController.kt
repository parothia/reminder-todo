package com.parothia.EmailVerification.controller

import com.parothia.EmailVerification.service.EmailVerificationService
import com.parothia.shared.AbstractService
import org.apache.logging.log4j.Level
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class EmailVerificationController : AbstractService() {
    @Autowired
    private lateinit var verificationTokenService: EmailVerificationService

    @PostMapping("/verifyEmail/{token}")
    fun verifyEmail(@RequestParam token: String) {
        logger.log(Level.INFO, "[EndPoint: /verifyEmail] <- [Request: {token = $token}]")
        verificationTokenService.verifyUserEmail(token)
    }
}