package com.example.manageSchool.repositories

import com.example.manageSchool.models.Session
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository: CrudRepository<Session, Long> {
}