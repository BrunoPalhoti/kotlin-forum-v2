package com.isadora.forum.repository

import com.isadora.forum.model.Topico
import com.isadora.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
    fun findByEmail(username: String?): Usuario?
}