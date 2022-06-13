package com.parothia.shared.dto

interface ResponseDTO : IDTO {
    val statusCode: Int
    val status: String
    val message: String
    val data: IDTO?
}