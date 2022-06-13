package com.parothia.usermanagement.dto

import com.parothia.shared.dto.IDTO
import java.time.ZonedDateTime

data class UserDTO(
    var id: Long,
    var username: String,
    var contact: String,
    var email: String,
    var status: String,
    var firstName: String,
    var lastName: String,
    var userType: String,
    var remindAt: ZonedDateTime? = null
) : IDTO
