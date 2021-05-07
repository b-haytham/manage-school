package com.example.manageSchool.controllers

import com.example.manageSchool.DTOS.student.AddStudentToGroupDTO
import com.example.manageSchool.DTOS.student.RegisterStudentDTO
import com.example.manageSchool.services.StudentServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/students")
class StudentController(
        private val studentService: StudentServiceImpl
) {
    @GetMapping
    fun findAll() = studentService.findAll()

    @GetMapping("/{studentId}/subject/{subjectId}")
    fun tauxAbsence(@PathVariable("studentId") studentId: Long, @PathVariable("subjectId") subjectId: Long) =
            studentService.tauxAbsence(studentId, subjectId)


    @PostMapping
    fun register(@RequestBody registerStudentDTO: RegisterStudentDTO) = studentService.register(registerStudentDTO)

    @PatchMapping("/group")
    fun addStudentsToGroup(@RequestBody addStudentToGroupDTO: AddStudentToGroupDTO) = studentService.addStudentsToGroup(addStudentToGroupDTO)
}