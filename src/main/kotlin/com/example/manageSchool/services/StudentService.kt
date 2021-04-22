package com.example.manageSchool.services

import com.example.manageSchool.DTOS.student.RegisterStudentDTO
import com.example.manageSchool.models.Student
import java.util.*

interface StudentService {

    fun findAll(): Iterable<Student>

    fun findById(id: Long): Optional<Student>

    fun register(registerStudentDTO: RegisterStudentDTO): Student
}