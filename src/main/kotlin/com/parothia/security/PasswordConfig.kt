package com.parothia.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

//@Configuration
//class PasswordConfig {
//
//    @Bean
//    fun PasswordEncoder(): BCryptPasswordEncoder {
//        return BCryptPasswordEncoder(10)
//    }
//}