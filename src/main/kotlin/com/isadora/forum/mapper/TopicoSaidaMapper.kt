package com.isadora.forum.mapper


import com.isadora.forum.dto.TopicoSaidaDto
import com.isadora.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoSaidaMapper: Mapper<Topico,TopicoSaidaDto>{
    override fun map(t: Topico): TopicoSaidaDto {
        return TopicoSaidaDto(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            status = t.status,
            dataCriacao = t.dataCriacao
        )
    }
}