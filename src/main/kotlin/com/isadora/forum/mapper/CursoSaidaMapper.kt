package com.isadora.forum.mapper

import com.isadora.forum.dto.CursoDto
import com.isadora.forum.model.Curso
import org.springframework.stereotype.Component

@Component
class CursoSaidaMapper: Mapper<Curso,CursoDto> {
    override fun map(t: Curso): CursoDto {
       return CursoDto(
           id = t.id,
           nome = t.nome,
           categoria = t.categoria
       )
    }
}