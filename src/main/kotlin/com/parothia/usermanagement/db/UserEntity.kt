package com.parothia.usermanagement.db

import com.parothia.shared.enum.UserActivationStatus
import com.parothia.shared.enum.UserType
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "Users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserIDSequence")
    @SequenceGenerator(name = "UserIDSequence", sequenceName = "UserIDSequence")
    var id: Long? = 0,
    @Column(nullable = false)
    var username: String,

    @Column(nullable = false)
    var contact: String,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: UserActivationStatus,

    @Column(nullable = false)
    var firstName: String,

    @Column(nullable = false)
    var lastName: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val userType: UserType,

    @Column(nullable = true)
    val remindAt: ZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC)

) {
    constructor() : this(null, "", "", "", UserActivationStatus.INACTIVE, "", "", UserType.USER)
    constructor(
        email: String,
        fullName: String,
        contact: String,
        firstName: String,
        lastName: String,
        userType: String
    ) : this(
        null,
        firstName + lastName,
        contact,
        email,
        UserActivationStatus.INACTIVE,
        firstName,
        lastName,
        UserType.USER,
    )
}


