package com.isadora.forum.controller

import com.isadora.forum.dto.UsuarioDto
import com.isadora.forum.service.UsuarioService
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
@RequestMapping("/usuers")
class UsuarioController(
    private val service: UsuarioService
) {

    @GetMapping
    fun list(): List<UsuarioDto>{
        return service.listAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): UsuarioDto{
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    fun save(@RequestBody @Valid usuarioDto: UsuarioDto, uriBuilder: UriComponentsBuilder): ResponseEntity<UsuarioDto>{
        val usuario = service.save(usuarioDto)
        val uri = uriBuilder.path("/usuers/${usuario.id}").build().toUri()
        return ResponseEntity.created(uri).body(usuario)
    }

}