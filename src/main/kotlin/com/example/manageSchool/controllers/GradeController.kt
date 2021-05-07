package com.example.manageSchool.controllers

import com.example.manageSchool.DTOS.grade.CreateGradeDTO
import com.example.manageSchool.DTOS.grade.PatchDsDTO
import com.example.manageSchool.services.GradeServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/grades")
class GradeController(
        private val gradeService: GradeServiceImpl
) {
    @GetMapping
    fun findAll() = gradeService.findAll()

    @GetMapping("/{gradeId}")
    fun findById(@PathVariable("gradeId") gradeId: Long) = gradeService.findById(gradeId)

    @PostMapping
    fun create(@RequestBody createGradeDTO: CreateGradeDTO) = gradeService.create(createGradeDTO)

    @PatchMapping("/{gradeId}/ds")
    fun patchDs(@PathVariable("gradeId") gradeId: Long, @RequestBody patchDsDTO: PatchDsDTO) = gradeService.patchDs(gradeId, patchDsDTO)
}