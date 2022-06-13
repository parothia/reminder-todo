package com.parothia.usermanagement.dto

import com.parothia.shared.dto.IDTO

data class LoginUserDTO(
    val email: String,
    val password: String
) : IDTO
