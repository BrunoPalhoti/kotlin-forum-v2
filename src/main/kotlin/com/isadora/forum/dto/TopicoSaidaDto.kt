package com.isadora.forum.dto

import com.isadora.forum.model.enum.StatusTopico
import java.time.LocalDateTime

data class TopicoSaidaDto(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
)