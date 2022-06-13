package com.parothia.usermanagement.controller

import com.parothia.shared.AbstractService
import com.parothia.shared.dto.GenericRequestDTO
import com.parothia.shared.dto.GenericSuccessResponseDTO
import com.parothia.shared.dto.ResponseDTO
import com.parothia.usermanagement.dto.UserDTO
import com.parothia.usermanagement.service.UserService
import org.apache.logging.log4j.Level
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/v1"], produces = ["application/json"])
class UserController : AbstractService() {
    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/user")
    fun createUser(@RequestBody request: GenericRequestDTO<UserDTO>): ResponseEntity<ResponseDTO> {
        logger.log(Level.INFO, "[Endpoint: /api/v1/user]<-[Request: ${request}]")
        val response = userService.createUser(request.data)
        return ResponseEntity.ok()
            .body(GenericSuccessResponseDTO(HttpStatus.OK.value(), "Success", "User Created", request.data))
    }

}