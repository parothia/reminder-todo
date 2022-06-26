package com.parothia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class ParothiaApplication {

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()
}

fun main(args: Array<String>) {
    runApplication<ParothiaApplication>(*args)
}
