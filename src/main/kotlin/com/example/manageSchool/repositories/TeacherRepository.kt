package com.example.manageSchool.repositories

import com.example.manageSchool.models.Teacher
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository : CrudRepository<Teacher, Long> {
}