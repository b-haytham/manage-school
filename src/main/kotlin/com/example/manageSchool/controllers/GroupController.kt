package com.example.manageSchool.controllers

import com.example.manageSchool.DTOS.group.CreateGroupDTO
import com.example.manageSchool.services.GroupServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/groups")
class GroupController(
    private val groupService: GroupServiceImpl
) {
    @GetMapping
    fun findAll() = groupService.findAll()

    @PostMapping
    fun create(@RequestBody createGroupDTO: CreateGroupDTO) = groupService.create(createGroupDTO)


}