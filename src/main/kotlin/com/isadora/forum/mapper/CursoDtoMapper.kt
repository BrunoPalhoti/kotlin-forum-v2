package com.isadora.forum.mapper

import com.isadora.forum.dto.CursoDto
import com.isadora.forum.model.Curso
import org.springframework.stereotype.Component

@Component
class CursoDtoMapper: Mapper<CursoDto,Curso> {
    override fun map(t: CursoDto): Curso{
      return Curso(
          id = t.id,
          nome = t.nome,
          categoria = t.categoria
      )
    }
}