package com.isadora.forum.service

import com.isadora.forum.dto.CursoDto
import com.isadora.forum.exception.NotFoundException
import com.isadora.forum.mapper.CursoDtoMapper
import com.isadora.forum.mapper.CursoSaidaMapper
import com.isadora.forum.model.Curso
import com.isadora.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CursoService(
    private val repository: CursoRepository,
    private val cursoDtoMapper: CursoDtoMapper,
    private val cursoSaidaMapper: CursoSaidaMapper
    ){

    fun listaAll(): List<CursoDto>{
        return repository.findAll().stream().map {
            t -> cursoSaidaMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun findCursoById(id: Long):CursoDto {
        val curso =  repository.findById(id)
            .orElseThrow{NotFoundException("Curso not found")}
        return cursoSaidaMapper.map(curso)
    }

    fun save(cursoDto: CursoDto): CursoDto {
        val curso = cursoDtoMapper.map(cursoDto)
        repository.save(curso)
        return cursoSaidaMapper.map(curso)
    }
}
