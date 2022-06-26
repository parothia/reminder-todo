package com.parothia.EmailVerification.db

import com.parothia.usermanagement.db.UserEntity
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "VerificationToken")
data class VerificationTokenEntity(
    @Column(nullable = false)
    val token: String,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    var user: UserEntity

) {
    companion object {
        const val EXPIRATION: Long = 24 * 60
    }

    @Column
    var expiry: ZonedDateTime = calculatedExpiryDate(EXPIRATION)

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null

    fun calculatedExpiryDate(timeInMinutes: Long): ZonedDateTime {
        return ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(timeInMinutes)
    }
}
