package com.isadora.forum.mapper

import com.isadora.forum.dto.UsuarioDto
import com.isadora.forum.model.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioSaidaMapper: Mapper<Usuario,UsuarioDto> {
    override fun map(t: Usuario): UsuarioDto {
        return UsuarioDto(
            id = t.id,
            nome = t.nome,
            email = t.email,
            password = t.password
        )
    }
}