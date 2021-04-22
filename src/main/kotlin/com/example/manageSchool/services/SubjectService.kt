package com.example.manageSchool.services

import com.example.manageSchool.DTOS.subject.CreateSubjectDTO
import com.example.manageSchool.models.Subject
import java.util.*

interface SubjectService {
    fun findAll(): Iterable<Subject>

    fun findById(id: Long): Optional<Subject>

    fun create(createSubjectDTO: CreateSubjectDTO): Subject
}