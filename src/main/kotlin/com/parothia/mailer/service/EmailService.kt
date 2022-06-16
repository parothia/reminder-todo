package com.parothia.mailer.service

import com.parothia.mailer.db.EmailDetails
import com.parothia.shared.dto.StringSuccessResponseDTO
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
interface EmailService {
    fun sendSimpleEmail(user: EmailDetails): StringSuccessResponseDTO
    fun sendAttatchmentsEmail(user: EmailDetails, file: MultipartFile): StringSuccessResponseDTO
}