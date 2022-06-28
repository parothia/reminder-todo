package com.parothia.usermanagement.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.parothia.shared.AbstractService
import com.parothia.shared.dto.GenericRequestDTO
import com.parothia.shared.dto.GenericSuccessResponseDTO
import com.parothia.shared.dto.ResponseDTO
import com.parothia.usermanagement.db.UserEntity
import com.parothia.usermanagement.dto.LoginUserDTO
import com.parothia.usermanagement.dto.UserDTO
import com.parothia.usermanagement.service.UserManagementService
import org.apache.logging.log4j.Level
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1"], produces = ["application/json"])
class UserController @Autowired constructor(val authenticationManager: AuthenticationManager) : AbstractService() {
    @Autowired
    private lateinit var userService: UserManagementService

    @PostMapping("/user")
    fun createUser(@Valid @RequestBody request: GenericRequestDTO<UserDTO>): ResponseEntity<ResponseDTO> {
        logger.log(Level.INFO, "[Endpoint: /api/v1/user]<-[Request: ${request}]")
        val response = userService.createUser(request.data)
        return ResponseEntity.ok()
            .body(GenericSuccessResponseDTO(HttpStatus.OK.value(), "Success", "User Created", request.data))
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginUserDTO, response: HttpServletResponse) {
        logger.log(Level.INFO, "login request")
        val authenticate: Authentication =
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.email, request.password))
        SecurityContextHolder.getContext().authentication
        val userDTO = userService.getUserByEmail(request.email)
        var user = authenticate.principal
        val roles = authenticate.authorities.map { it -> it.authority }

        response.writer?.write(
            "{" +
                    "\"statusCode\": 200," +
                    "\"status\": \"success\"," +
                    "\"message\": \"Authentication success\"," +
                    "\"data\": {" +
                    "\"name\": \"" + userDTO.firstName + userDTO.lastName + "\"," +
                    "\"id\": \"" + userDTO.id + "\"," +
                    "\"contact\": \"" + userDTO.contact + "\"," +
                    "\"email\": \"" + userDTO.email + "\"}"
        )
    }

    @GetMapping("/allUsers")
    fun getAllUsers(): List<UserEntity> {
        return userService.getAllUsers()
    }

    @GetMapping("/ap")
    fun getAllUser(): List<UserEntity> {
        return userService.getAllUsers()
    }

}