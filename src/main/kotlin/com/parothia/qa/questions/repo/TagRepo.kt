package com.parothia.qa.questions.repo

import com.parothia.qa.questions.db.TagEntity
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepo : JpaRepository<TagEntity, Long> {

    fun findByTagName(tagName: String)
}