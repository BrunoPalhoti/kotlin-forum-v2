package com.isadora.forum.service

import com.isadora.forum.dto.TopicoAtualizacaoDto
import com.isadora.forum.dto.TopicoDto
import com.isadora.forum.dto.TopicoPorCategoriaDto
import com.isadora.forum.dto.TopicoSaidaDto
import com.isadora.forum.exception.NotFoundException
import com.isadora.forum.mapper.TopicoDtoMapper
import com.isadora.forum.mapper.TopicoSaidaMapper
import com.isadora.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoSaidaMapper: TopicoSaidaMapper,
    private val topicoDtoMapper: TopicoDtoMapper,
    private val notFoundMessage: String = "Topic not found!"
    ) {

    fun listALL(nomeCurso: String?, paginacao: Pageable): Page<TopicoSaidaDto> {
        val topicos =  if (nomeCurso.equals(null)){
            repository.findAll(paginacao)
        }else{
            repository.findByCursoNome(nomeCurso,paginacao)
        }
        return topicos.map { t ->
            topicoSaidaMapper.map(t)
        }
    }

    fun findById(id: Long): TopicoSaidaDto{
        val topico = repository.findById(id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        return topicoSaidaMapper.map(topico)
    }

    fun save(topicoDto: TopicoDto): TopicoSaidaDto{
        val topico = topicoDtoMapper.map(topicoDto)
        repository.save(topico)
        return topicoSaidaMapper.map(topico)
    }

    fun update( topicoAtualizacaoDto: TopicoAtualizacaoDto): TopicoSaidaDto {
        val topico = repository.findById(topicoAtualizacaoDto.id).stream().filter { t ->
            t.id == topicoAtualizacaoDto.id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}
        topico.titulo = topicoAtualizacaoDto.titulo
        topico.mensagem = topicoAtualizacaoDto.mensagem
        return topicoSaidaMapper.map(topico)
    }

    fun delete(id: Long) {
        return repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }
}