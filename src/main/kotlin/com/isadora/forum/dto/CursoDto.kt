package com.isadora.forum.dto

import javax.validation.constraints.NotEmpty

data class CursoDto(

    val id: Long?,

    @field:NotEmpty(message = "Nome nao pode ser em branco!")
    val nome: String,

    @field:NotEmpty(message = "Categoria nao pode ser em branco!")
    val categoria: String
)