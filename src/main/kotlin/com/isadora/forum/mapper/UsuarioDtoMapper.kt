package com.isadora.forum.mapper

import com.isadora.forum.dto.UsuarioDto
import com.isadora.forum.model.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioDtoMapper: Mapper<UsuarioDto,Usuario> {
    override fun map(t: UsuarioDto): Usuario {
       return Usuario(
           id = t.id,
           nome = t.nome,
           email = t.email,
           password = t.password
       )
    }
}