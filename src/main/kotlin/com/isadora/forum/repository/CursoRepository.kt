package com.isadora.forum.repository

import com.isadora.forum.model.Curso
import com.isadora.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {

}