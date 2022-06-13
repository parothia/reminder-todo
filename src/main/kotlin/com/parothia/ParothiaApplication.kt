package com.parothia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParothiaApplication

fun main(args: Array<String>) {
    runApplication<ParothiaApplication>(*args)
}
