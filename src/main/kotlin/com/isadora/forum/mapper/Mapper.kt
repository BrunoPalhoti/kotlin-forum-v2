package com.isadora.forum.mapper

import com.isadora.forum.dto.CursoDto

interface Mapper<T,U> {

    fun map(t: T): U
}