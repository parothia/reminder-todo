package com.parothia.shared.dto

interface RequestDTO<DataClass> : IDTO {
    val requestId: String
    val data: DataClass
}