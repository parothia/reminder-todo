package com.parothia.usermanagement.dto

import com.parothia.shared.dto.IDTO
import com.parothia.shared.enum.UserActivationStatus
import com.parothia.shared.enum.UserType
import java.time.ZonedDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

data class UserDTO(
    var id: Long?,
    var username: String,
    var password: String,
    @Pattern(regexp = "[0-9]{10}$")
    var contact: String,
    @Email
    var email: String,
    var status: UserActivationStatus,
    var firstName: String,
    var lastName: String,
    var userType: UserType,
    var remindAt: ZonedDateTime? = null
) : IDTO {
    constructor() : this(null, "", "", "", "", UserActivationStatus.INACTIVE, "", "", UserType.USER)
}
