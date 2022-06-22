package com.parothia.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class MySecurityConfig : WebSecurityConfigurerAdapter() {

//    @Autowired
//    private lateinit var passwordConfig: PasswordConfig;


    override fun configure(http: HttpSecurity?) {
        //basic auth no logout and every time u need to send username and password -- popup
        http!!
            .authorizeRequests()
            .antMatchers("/", "index", "/css/*") // patterns specified which u want to make public
            .permitAll() //allow specified patterns in antmatchers
            .antMatchers("/api/v1/**").hasRole(UserRole.ADMIN.name)
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }

    override fun userDetailsService(): UserDetailsService { //how u retreive users from database
        val user: UserDetails = User.builder() // this is creating user and using ImMemoruDb
            .username("anurag")
            .password(BCryptPasswordEncoder().encode("password")) //password must be encoded
            .roles(UserRole.USER.name) // internally ROLE_USER
            .build()

        val user1: UserDetails = User.builder() // this is creating user and using ImMemoruDb
            .username("parothia")
            .password(BCryptPasswordEncoder().encode("password")) //password must be encoded
            .roles(UserRole.ADMIN.name) // internally ROLE_USER
            .build()
        return InMemoryUserDetailsManager(user, user1)
    }
}