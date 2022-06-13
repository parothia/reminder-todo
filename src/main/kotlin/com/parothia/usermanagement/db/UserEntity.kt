package com.parothia.usermanagement.db

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
    val contact: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val status: String = "",

    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val lastName: String,

    @Column(nullable = false)
    val userType: String = "",

    @Column(nullable = false)
    val remindAt: ZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC)


)

//enum class UserType {
//    ADMIN, USER
//}
