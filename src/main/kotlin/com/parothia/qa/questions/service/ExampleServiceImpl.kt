package com.parothia.qa.questions.service

import com.parothia.qa.questions.repo.ExampleRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExampleServiceImpl : ExampleService {

    @Autowired
    private lateinit var exampleRepo: ExampleRepo
}