package com.parothia.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

data class MyUserDetails(
    private val username: String,
    private val password: String,
    private val isEnabled: Boolean, //Disabled account can not log in
    private val isCredentialsNonExpired: Boolean, //credential can be expired,eg. Change the password every three months
    private val isAccountNonExpired: Boolean, //eg. Demo account（guest） can only be online  24 hours
    private val isAccountNonLocked: Boolean, //eg. Users who malicious attack system,lock their account for one year
    private val authorities: Set<GrantedAuthority>,
) : UserDetails {
    override fun getUsername(): String = username
    override fun getPassword(): String = BCryptPasswordEncoder().encode("pass")
    override fun isEnabled(): Boolean = isEnabled
    override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpired
    override fun isAccountNonExpired(): Boolean = isAccountNonExpired
    override fun isAccountNonLocked(): Boolean = isAccountNonLocked
    override fun getAuthorities(): Set<out GrantedAuthority> =
        setOf<GrantedAuthority>(SimpleGrantedAuthority("ROLE_USER"))
}