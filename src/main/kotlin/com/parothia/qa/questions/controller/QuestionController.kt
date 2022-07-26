package com.parothia.qa.questions.controller

import com.parothia.qa.questions.service.ExampleService
import com.parothia.qa.questions.service.QuestionService
import com.parothia.qa.questions.service.TagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class QuestionController(
    @Autowired questionService: QuestionService,
    @Autowired tagService: TagService,
    @Autowired exampleService: ExampleService
) {


    @GetMapping("/questions")
    fun getAllQuestions() {

    }

    @PostMapping("/question/post")
    fun postQuestion() {

    }

    @PostMapping("/question/delete")
    fun deleteQuestion() {

    }

    @PostMapping("/tag/add")
    fun addTag() {

    }
}