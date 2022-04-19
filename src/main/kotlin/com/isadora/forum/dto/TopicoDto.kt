package com.isadora.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class TopicoDto(

    @field:NotEmpty(message = "Titulo nao pode ser em branco!")
    val titulo: String,

    @field:NotEmpty(message = "Mensagem nao pode ser em branco!")
    val mensagem: String,

    @field:NotNull(message = "Curso nao pode ser em branco!")
    val idCurso: Long,

    @field:NotNull(message = "Autor nao pode ser em branco!")
    val idAutor: Long,
)