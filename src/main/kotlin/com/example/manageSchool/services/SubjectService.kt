package com.example.manageSchool.services

import com.example.manageSchool.DTOS.subject.AddSubjectsToGroupsDTO
import com.example.manageSchool.DTOS.subject.CreateSubjectDTO
import com.example.manageSchool.models.Subject
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface SubjectService {
    fun findAll(): Iterable<Subject>

    fun findById(id: Long): Optional<Subject>

    fun findAllById(ids: Iterable<Long>): Iterable<Subject>

    fun create(createSubjectDTO: CreateSubjectDTO): Subject

    fun addSubjectsToGroup(addSubjectsToGroupsDTO: AddSubjectsToGroupsDTO): Iterable<Subject>
}