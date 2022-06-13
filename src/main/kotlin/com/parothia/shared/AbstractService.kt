package com.parothia.shared

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


abstract class AbstractService {
    val logger: Logger = LogManager.getLogger(javaClass)
}