package com.parothia.qa.questions.service

import com.parothia.qa.questions.repo.QuestionRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuestionServiceImpl : QuestionService {

    @Autowired
    private lateinit var questionRepo: QuestionRepo
}