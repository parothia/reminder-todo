package com.parothia.shared.dto

data class GenericRequestDTO<DataClass>(
    override val requestId: String,
    override val data: DataClass
) : RequestDTO<DataClass>
