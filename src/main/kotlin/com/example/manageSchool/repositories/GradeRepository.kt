package com.example.manageSchool.repositories

import com.example.manageSchool.models.Grade
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GradeRepository : CrudRepository<Grade, Long> {
}