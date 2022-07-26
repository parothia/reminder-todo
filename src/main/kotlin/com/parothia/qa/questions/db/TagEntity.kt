package com.parothia.qa.questions.db

import javax.persistence.*

@Entity(name = "tag_entity")
class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "tag")
    var tag: String = ""

}