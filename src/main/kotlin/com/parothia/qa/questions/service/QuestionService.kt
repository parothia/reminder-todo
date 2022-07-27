package com.parothia.qa.questions.service

import com.parothia.qa.questions.db.QuestionEntity
import com.parothia.qa.questions.dto.CreateQuestionRequestDTO
import com.parothia.qa.questions.dto.DeleteQuestionRequestDTO
import com.parothia.qa.questions.dto.GetQuestionRequestDTO
import org.springframework.stereotype.Service

@Service
interface QuestionService {
    fun createQuestion(request: CreateQuestionRequestDTO)
    fun getAllQuestions(): MutableList<QuestionEntity>
    fun deleteQuestion(id: Long)

    fun getQuestion(id: Long): QuestionEntity
}