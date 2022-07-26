package com.parothia.qa.questions.repo

import com.parothia.qa.questions.db.ExampleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExampleRepo : JpaRepository<ExampleEntity, Long> {
}