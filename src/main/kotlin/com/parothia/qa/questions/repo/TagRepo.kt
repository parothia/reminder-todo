package com.parothia.qa.questions.repo

import com.parothia.qa.questions.db.TagEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepo : JpaRepository<TagEntity, Long> {
}