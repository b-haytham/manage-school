package com.example.manageSchool.repositories

import com.example.manageSchool.models.Absence
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AbsenceRepository: CrudRepository<Absence, Long> {
}