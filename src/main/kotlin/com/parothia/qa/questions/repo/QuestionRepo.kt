package com.parothia.qa.questions.repo

import com.parothia.qa.questions.db.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepo : JpaRepository<QuestionEntity, Long> {
}