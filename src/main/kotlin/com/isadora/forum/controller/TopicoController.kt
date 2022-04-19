package com.isadora.forum.controller

import com.isadora.forum.dto.TopicoAtualizacaoDto
import com.isadora.forum.dto.TopicoDto
import com.isadora.forum.dto.TopicoPorCategoriaDto
import com.isadora.forum.dto.TopicoSaidaDto
import com.isadora.forum.service.TopicoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicoController(
    private val service: TopicoService
) {

    @GetMapping()
    @Cacheable("topicos")
    fun list(@RequestParam(required = false) nomeCurso: String?,
             @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoSaidaDto>{
        return service.listALL(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    @Cacheable("findyTopicosById")
    fun findById(@PathVariable id: Long): TopicoSaidaDto{
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos", "findyTopicosById","relatorio"], allEntries = true)
    fun save(@RequestBody @Valid topicoDto: TopicoDto, uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoSaidaDto>{
        val topicoSaidaDto = service.save(topicoDto)
        val uri = uriBuilder.path("/topics/${topicoSaidaDto.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoSaidaDto)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topicos", "findyTopicosById","relatorio"], allEntries = true)
    fun update(@RequestBody @Valid topicoAtualizacaoDto: TopicoAtualizacaoDto): ResponseEntity<TopicoSaidaDto>{
        val topicoSaida = service.update(topicoAtualizacaoDto)
        return ResponseEntity.ok(topicoSaida)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topicos", "findyTopicosById","relatorio"], allEntries = true)
    fun delete(@PathVariable id: Long){
        service.delete(id)
    }

    @GetMapping("/relatorio")
    @Cacheable("relatorio")
    fun relatorio(): List<TopicoPorCategoriaDto>{
        return service.relatorio()
    }

}