package com.parothia.security

import com.parothia.usermanagement.service.UserManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class MySecurityConfig @Autowired constructor(
    val passwordEncoder: BCryptPasswordEncoder,
    val userManagementService: UserManagementService,

    val dataSource: DataSource
) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/index", "/api/v1/login").permitAll()
            .antMatchers("/api/v1/**").hasRole("USER")
            .antMatchers("/api/v1/**").hasRole("ADMIN")
            .anyRequest()
            .authenticated()
            .and()
            .logout()
            .logoutUrl("/api/v1/logout")
            .and()
            .cors()
    }

    @Bean
    fun corsConfiguraitonSource(): CorsConfigurationSource {
        val configuration: CorsConfiguration = CorsConfiguration().apply {
            allowedHeaders = listOf("X-Requested-With", "Origin", "Content-Type", "Accept")
            allowedMethods = listOf("OPTIONS", "POST", "DELETE", "GET", "PUT")
            allowedOrigins = listOf("http://localhost:3000")
            allowCredentials = true
        }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/api/**", configuration)
        return source
    }


    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userManagementService)
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }

}