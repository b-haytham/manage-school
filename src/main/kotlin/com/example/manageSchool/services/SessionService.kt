package com.example.manageSchool.services

import com.example.manageSchool.DTOS.session.CreateSessionDTO
import com.example.manageSchool.models.Session
import java.util.*

interface SessionService {

    fun findAll(): Iterable<Session>

    fun findById(id: Long): Optional<Session>

    fun findAllById(ids: Iterable<Long>): Iterable<Session>

    fun save(session: Session): Session

    fun create(createSessionDTO: CreateSessionDTO): Session
}