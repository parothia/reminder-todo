package com.parothia.qa.questions.dto

data class CreateQuestionRequestDTO(
    val title: String,
    val description: String,
    val difficulty: String,
    val tags: List<String>,
    val examples: List<CreateExampleRequestDTO>
)
