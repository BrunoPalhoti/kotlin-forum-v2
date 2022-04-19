package com.isadora.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class TopicoAtualizacaoDto(

    @field:NotNull
    var id: Long,

    @field:NotEmpty
    val titulo: String,

    @field:NotEmpty
    val mensagem: String
)
