package com.parothia.usermanagement.dto

import com.parothia.shared.dto.IDTO
import java.time.ZonedDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

data class UserDTO(
    var id: Long,
    var username: String,
    @Pattern(regexp = "[0-9]{10}$")
    var contact: String,
    @Email
    var email: String,
    var status: String,
    var firstName: String,
    var lastName: String,
    var userType: String,
    var remindAt: ZonedDateTime? = null
) : IDTO
