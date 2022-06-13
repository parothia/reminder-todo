package com.parothia.shared.dto

class GenericSuccessResponseDTO(
    override val statusCode: Int,
    override val status: String,
    override val message: String,
    override val data: IDTO?
) : ResponseDTO