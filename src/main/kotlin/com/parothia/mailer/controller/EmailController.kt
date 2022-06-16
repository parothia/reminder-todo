package com.parothia.mailer.controller

import com.parothia.mailer.db.EmailDetails
import com.parothia.mailer.service.EmailService
import com.parothia.shared.AbstractService
import com.parothia.shared.dto.GenericSuccessResponseDTO
import org.apache.logging.log4j.Level
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Controller
@RestController
class EmailController : AbstractService() {
    @Autowired
    private lateinit var emailService: EmailService

    @PostMapping("/sendSimpleEmail")
    fun sendSimpleEmail(@RequestBody user: EmailDetails) {
        logger.log(Level.INFO, "Email Controller Request ");
        emailService.sendSimpleEmail(user)
    }

    @PostMapping("/sendAttachEmail", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun sendAttachEmail(
        user: EmailDetails,
        @RequestParam(name = "file", required = true) file: MultipartFile
    ): ResponseEntity<GenericSuccessResponseDTO> {
        logger.log(Level.INFO, "Email Controller Request ");
        val response = emailService.sendAttatchmentsEmail(user, file)
        logger.log(Level.INFO, "Email Controller Response ");
        return ResponseEntity.ok().body(
            GenericSuccessResponseDTO(
                HttpStatus.ACCEPTED.value(),
                "Success",
                "Email sent successfully",
                response
            )
        )
    }
}