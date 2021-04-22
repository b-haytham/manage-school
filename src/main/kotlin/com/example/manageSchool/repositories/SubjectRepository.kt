package com.example.manageSchool.repositories

import com.example.manageSchool.models.Subject
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository: CrudRepository<Subject, Long> {
}