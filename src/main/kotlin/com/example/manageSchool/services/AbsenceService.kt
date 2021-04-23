package com.example.manageSchool.services

import com.example.manageSchool.DTOS.absence.CreateAbsenceDTO
import com.example.manageSchool.models.Absence
import java.util.*

interface AbsenceService {

    fun findAll(): Iterable<Absence>
    fun findAllById(ids: Iterable<Long>): Iterable<Absence>
    fun findById(id: Long): Optional<Absence>
    fun save(absence: Absence): Absence
    fun create(createAbsenceDTO: CreateAbsenceDTO): Absence
}