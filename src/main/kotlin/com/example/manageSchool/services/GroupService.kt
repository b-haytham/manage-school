package com.example.manageSchool.services


import com.example.manageSchool.DTOS.group.CreateGroupDTO
import com.example.manageSchool.models.Group
import java.util.*

interface GroupService {
    fun findAll(): Iterable<Group>

    fun findById(id: Long): Optional<Group>

    fun create(createGroupDTO: CreateGroupDTO): Group

}