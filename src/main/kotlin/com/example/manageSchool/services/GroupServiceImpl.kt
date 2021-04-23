package com.example.manageSchool.services


import com.example.manageSchool.DTOS.group.CreateGroupDTO
import com.example.manageSchool.models.Group
import com.example.manageSchool.repositories.GroupRepository
import org.springframework.stereotype.Service

import java.util.*

@Service
class GroupServiceImpl(
    private val groupRepository: GroupRepository,
    //private val subjectService: SubjectServiceImpl,
): GroupService {
    override fun findAll(): Iterable<Group> = groupRepository.findAll()

    override fun findById(id: Long): Optional<Group> = groupRepository.findById(id)

    override fun create(createGroupDTO: CreateGroupDTO): Group = groupRepository.save(Group(name = createGroupDTO.name, level = createGroupDTO.level))

}