package com.parothia.mailer.db

import org.springframework.core.io.InputStreamSource
import org.springframework.web.multipart.MultipartFile
import java.io.File


data class EmailDetails(
    var to: String,
    var from: String,
    var subject: String,
    var content: String,
    var attatchment: List<String>?,
)