package com.example.manageSchool.controllers

import com.example.manageSchool.DTOS.subject.AddSubjectsToGroupsDTO
import com.example.manageSchool.DTOS.subject.CreateSubjectDTO
import com.example.manageSchool.services.SubjectServiceImpl
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/subjects")
class SubjectController(
    private val subjectService: SubjectServiceImpl
) {

    @GetMapping
    fun findAll() = subjectService.findAll()

    @PostMapping
    fun create(@RequestBody createSubjectDTO: CreateSubjectDTO) = subjectService.create(createSubjectDTO)

    @PatchMapping("/group")
    fun addSubjectsToGroup(@RequestBody addSubjectsToGroupsDTO: AddSubjectsToGroupsDTO) = subjectService.addSubjectsToGroup(addSubjectsToGroupsDTO)
}