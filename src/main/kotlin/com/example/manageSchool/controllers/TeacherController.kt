package com.example.manageSchool.controllers

import com.example.manageSchool.DTOS.teacher.RegisterTeacherDTO
import com.example.manageSchool.services.TeacherServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/teachers")
class TeacherController(
        private val teacherService: TeacherServiceImpl
) {

    @GetMapping
    fun findAll() = teacherService.findAll()

    @PostMapping
    fun register(@RequestBody registerTeacherDTO: RegisterTeacherDTO) = teacherService.register(registerTeacherDTO)
}