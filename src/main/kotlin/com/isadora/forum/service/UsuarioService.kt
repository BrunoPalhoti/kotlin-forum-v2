package com.isadora.forum.service

import com.isadora.forum.dto.UsuarioDto
import com.isadora.forum.exception.NotFoundException
import com.isadora.forum.mapper.UsuarioDtoMapper
import com.isadora.forum.mapper.UsuarioSaidaMapper
import com.isadora.forum.model.Usuario
import com.isadora.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class UsuarioService(
    private val repository: UsuarioRepository,
    private val usuarioDtoMapper: UsuarioDtoMapper,
    private val usuarioSaidaMapper: UsuarioSaidaMapper
    ) : UserDetailsService {

    fun listAll(): List<UsuarioDto>{
        return repository.findAll().stream().map {
            t -> usuarioSaidaMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun findById(id: Long): UsuarioDto{
        val usuario = repository.findById(id)
            .orElseThrow{NotFoundException("Usuario not found")}
        return usuarioSaidaMapper.map(usuario)
    }

    fun save(usuarioDto: UsuarioDto): UsuarioDto{
        val usuario = usuarioDtoMapper.map(usuarioDto)
        repository.save(usuario)
        return usuarioSaidaMapper.map(usuario)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw RuntimeException("Email não encontrado")
        return UserDetail(usuario)
    }
}
