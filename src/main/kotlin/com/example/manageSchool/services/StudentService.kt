package com.example.manageSchool.services

import com.example.manageSchool.DTOS.student.AddStudentToGroupDTO
import com.example.manageSchool.DTOS.student.RegisterStudentDTO
import com.example.manageSchool.DTOS.student.TauxAbsenceResponse
import com.example.manageSchool.models.Student
import java.util.*

interface StudentService {

    fun findAll(): Iterable<Student>

    fun tauxAbsence(studentId: Long, subjectId: Long): TauxAbsenceResponse

    fun findAllById(ids: Iterable<Long>): Iterable<Student>

    fun findById(id: Long): Optional<Student>

    fun save(student: Student): Student

    fun register(registerStudentDTO: RegisterStudentDTO): Student

    fun addStudentsToGroup(addStudentToGroupDTO: AddStudentToGroupDTO): Iterable<Student>
}