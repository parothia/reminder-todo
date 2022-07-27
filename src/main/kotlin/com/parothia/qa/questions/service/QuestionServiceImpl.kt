package com.parothia.qa.questions.service

import com.parothia.qa.questions.db.ExampleEntity
import com.parothia.qa.questions.db.QuestionEntity
import com.parothia.qa.questions.db.TagEntity
import com.parothia.qa.questions.dto.CreateQuestionRequestDTO
import com.parothia.qa.questions.repo.ExampleRepo
import com.parothia.qa.questions.repo.QuestionRepo
import com.parothia.qa.questions.repo.TagRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuestionServiceImpl : QuestionService {

    @Autowired
    private lateinit var questionRepo: QuestionRepo

    @Autowired
    private lateinit var exampleRepo: ExampleRepo

    @Autowired
    private lateinit var tagRepo: TagRepo
    override fun createQuestion(request: CreateQuestionRequestDTO) {
        var question = QuestionEntity()
        val Examples: List<ExampleEntity> = mutableListOf()
        val Tags: List<TagEntity> = mutableListOf()
        request.examples.forEach {
            var examp = exampleRepo.save(ExampleEntity(it.example))
            Examples.plus(exampleRepo.save(examp))
        }
        request.tags.forEach {
            var tag = tagRepo.findByTagName(it)
            Tags.plus(tag)
        }
        with(request) {
            question.title = title
            question.description = description
            question.tags = Tags
            question.examples = Examples
            question.type = difficulty
        }
        questionRepo.save(question)
    }

    override fun getAllQuestions(): MutableList<QuestionEntity> {
        return questionRepo.findAll()
    }

    override fun deleteQuestion(id: Long) {
        questionRepo.deleteById(id)
    }

    override fun getQuestion(id: Long): QuestionEntity {
        return questionRepo.findById(id).get()
    }
}