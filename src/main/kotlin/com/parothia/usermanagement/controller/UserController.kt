package com.parothia.usermanagement.controller

import com.parothia.shared.AbstractService
import com.parothia.shared.dto.GenericRequestDTO
import com.parothia.shared.dto.GenericSuccessResponseDTO
import com.parothia.shared.dto.ResponseDTO
import com.parothia.usermanagement.db.UserEntity
import com.parothia.usermanagement.dto.UserDTO
import com.parothia.usermanagement.service.UserManagementService
import org.apache.logging.log4j.Level
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1"], produces = ["application/json"])
class UserController : AbstractService() {
    @Autowired
    private lateinit var userService: UserManagementService

    @PostMapping("/user")
    fun createUser(@Valid @RequestBody request: GenericRequestDTO<UserDTO>): ResponseEntity<ResponseDTO> {
        logger.log(Level.INFO, "[Endpoint: /api/v1/user]<-[Request: ${request}]")
        val response = userService.createUser(request.data)
        return ResponseEntity.ok()
            .body(GenericSuccessResponseDTO(HttpStatus.OK.value(), "Success", "User Created", request.data))
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