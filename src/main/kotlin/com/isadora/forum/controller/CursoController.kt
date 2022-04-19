package com.isadora.forum.controller

import com.isadora.forum.dto.CursoDto
import com.isadora.forum.service.CursoService
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/cursos")
class CursoController(
    private val service: CursoService
) {

    @GetMapping
    fun listALl(): List<CursoDto>{
        return service.listaAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CursoDto{
        return service.findCursoById(id)
    }

    @PostMapping
    @Transactional
    fun save(@RequestBody @Valid cursoDto: CursoDto, uriBuilder: UriComponentsBuilder): ResponseEntity<CursoDto>{
        val curso = service.save(cursoDto)
        val uri = uriBuilder.path("/topics/${cursoDto.id}").build().toUri()
        return ResponseEntity.created(uri).body(curso)
    }
}