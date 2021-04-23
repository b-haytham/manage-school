package com.example.manageSchool.services

import com.example.manageSchool.DTOS.grade.CreateGradeDTO
import com.example.manageSchool.DTOS.grade.PatchDsDTO
import com.example.manageSchool.models.Grade
import java.util.*

interface GradeService {
    fun findAll(): Iterable<Grade>
    fun findAllById(ids: Iterable<Long>): Iterable<Grade>
    fun findById(id: Long): Grade
    fun save(grade: Grade): Grade
    fun create(createGradeDTO: CreateGradeDTO): Grade
    fun patchDs(gradeId: Long, patchDsDTO: PatchDsDTO): Grade
}