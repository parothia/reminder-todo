package com.parothia.security

import com.parothia.usermanagement.service.UserManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class MySecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    @Autowired
    private lateinit var userManagementService: UserManagementService

    private lateinit var dataSource: DataSource
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/index", "/css/")  //patterns make public
            .permitAll()
            .antMatchers("/api/v1/**").hasRole("USER")
            .antMatchers("/api/v1/**").hasRole("ADMIN")
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
    }


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userManagementService)
    }
}