package com.parothia.qa.questions.db

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Entity(name = "question_entity")
class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "title")
    var title: String = ""

    @Column(name = "type")
    var type: String = ""

    @Column(name = "description")
    var description: String = ""

    @OneToMany
    @JoinColumn(name = "tag_id")
    lateinit var tags: List<TagEntity>

    @OneToMany
    @JoinColumn(name = "example_id")
    lateinit var examples: List<ExampleEntity>
}