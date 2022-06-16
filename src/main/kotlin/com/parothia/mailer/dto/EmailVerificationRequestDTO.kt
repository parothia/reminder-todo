package com.parothia.mailer.dto

import javax.security.auth.Subject

data class EmailVerificationRequestDTO(
    val to: String,
    val from: String,
    val subject: String,
    val body: String?,
    val verifyLink: String,
    val name: String,
)
