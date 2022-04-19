package com.isadora.forum.mapper

import com.isadora.forum.dto.TopicoDto
import com.isadora.forum.model.Topico
import com.isadora.forum.service.UsuarioService
import com.isadora.forum.service.CursoService
import org.springframework.stereotype.Component

@Component
class TopicoDtoMapper(
    private val cursoService: CursoService,
    private val cursoDtoMapper: CursoDtoMapper,
    private val usuarioService: UsuarioService,
    private val usuarioDtoMapper: UsuarioDtoMapper
): Mapper<TopicoDto,Topico> {
    override fun map(t: TopicoDto): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoDtoMapper.map(cursoService.findCursoById(t.idCurso)),
            autor = usuarioDtoMapper.map(usuarioService.findById(t.idAutor))
        )
    }
}