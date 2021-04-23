package com.example.manageSchool.repositories

import com.example.manageSchool.models.Group
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository: CrudRepository<Group, Long> {
}