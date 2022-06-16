package com.parothia.mailer.service

import com.parothia.mailer.db.EmailDetails
import com.parothia.shared.dto.StringSuccessResponseDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.mail.MailException
import org.springframework.mail.MailSendException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class EmailServiceImpl : EmailService {

    @Autowired
    private lateinit var javaMailSender: JavaMailSender
    override fun sendSimpleEmail(user: EmailDetails): StringSuccessResponseDTO {
        try {
            val simpleMailMessage = SimpleMailMessage()
            simpleMailMessage.setTo(user.to)
            simpleMailMessage.setFrom(user.from)
            simpleMailMessage.setSubject(user.subject)
            simpleMailMessage.setText(user.content)
            javaMailSender.send(simpleMailMessage)
            return StringSuccessResponseDTO("Email sent to ${user.to}")
        } catch (ex: MailException) {
            throw MailSendException("Failed to send Email")
        }
    }

    override fun sendAttatchmentsEmail(user: EmailDetails, file: MultipartFile): StringSuccessResponseDTO {
        try {
            val mimeMessage = javaMailSender.createMimeMessage()
            var mimeMessageHelper = MimeMessageHelper(mimeMessage, true, "UTF-8")
            mimeMessageHelper.setTo(user.to)
            mimeMessageHelper.setFrom(user.from)
            mimeMessageHelper.setSubject(user.subject)
            mimeMessageHelper.setText(user.content, false)
            file.originalFilename?.let {
                mimeMessageHelper.addAttachment(
                    it, file
                )
            }
            javaMailSender.send(mimeMessage)
            return StringSuccessResponseDTO("Email sent to ${user.to}")
        } catch (ex: MailException) {
            throw MailSendException("Failed to send Attachments Email")
        }
    }
}