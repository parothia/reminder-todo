package com.parothia.qa.questions.controller

import com.parothia.qa.questions.db.CreateTagRequestDTO
import com.parothia.qa.questions.dto.CreateQuestionRequestDTO
import com.parothia.qa.questions.dto.DeleteQuestionRequestDTO
import com.parothia.qa.questions.dto.GetQuestionRequestDTO
import com.parothia.qa.questions.service.ExampleService
import com.parothia.qa.questions.service.QuestionService
import com.parothia.qa.questions.service.TagService
import com.parothia.shared.AbstractService
import org.apache.logging.log4j.Level
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class QuestionController(
    val questionService: QuestionService,
    val tagService: TagService,
    val exampleService: ExampleService
) : AbstractService() {


    @GetMapping("/questions")
    fun getAllQuestions() {
        logger.log(Level.INFO, "request->/api/v1/questions")
        val response = questionService.getAllQuestions()
    }

    @PostMapping("/question/create")
    fun createQuestion(@RequestBody request: CreateQuestionRequestDTO) {
        logger.log(Level.INFO, "request->/api/v1/question/create $request ")
        val response = questionService.createQuestion(request)
    }

    @PostMapping("/question/get")
    fun getQuestion(@RequestBody request: GetQuestionRequestDTO) {
        logger.log(Level.INFO, "request->/api/v1/question $request")

        val response = questionService.getQuestion(request.id)
    }

    @PostMapping("/question/delete")
    fun deleteQuestion(@RequestBody request: DeleteQuestionRequestDTO) {
        logger.log(Level.INFO, "request->/api/v1/question/delete $request")
        questionService.deleteQuestion(request.id)
    }

    @PostMapping("/tag/add")
    fun addTag(@RequestBody request: CreateTagRequestDTO) {
        logger.log(Level.INFO, "request->/api/v1/tag/add $request")
        tagService.addTag(request.tagName)
    }
}