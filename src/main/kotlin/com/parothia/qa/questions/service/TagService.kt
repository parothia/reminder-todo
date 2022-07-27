package com.parothia.qa.questions.service

import com.parothia.qa.questions.db.CreateTagRequestDTO
import org.springframework.stereotype.Service

@Service
interface TagService {
    fun addTag(tagName: String)
}