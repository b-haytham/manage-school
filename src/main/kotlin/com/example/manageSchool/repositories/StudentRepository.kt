package com.example.manageSchool.repositories

import com.example.manageSchool.models.Student
import org.springframework.data.repository.CrudRepository

interface StudentRepository: CrudRepository<Student, Long> {
}