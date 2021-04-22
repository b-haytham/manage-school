package com.example.manageSchool.services

import com.example.manageSchool.DTOS.teacher.RegisterTeacherDTO
import com.example.manageSchool.models.Teacher
import java.util.*

interface TeacherService {

    fun findAll(): Iterable<Teacher>

    fun findById(id: Long): Optional<Teacher>

    fun register(registerTeacherDTO: RegisterTeacherDTO): Teacher
}