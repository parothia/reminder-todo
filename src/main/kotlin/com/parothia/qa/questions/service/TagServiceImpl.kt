package com.parothia.qa.questions.service

import com.parothia.qa.questions.repo.TagRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TagServiceImpl : TagService {

    @Autowired
    private lateinit var tagRepo: TagRepo

    override fun addTag(tagName: String) {
        TODO("Not yet implemented")
    }
}