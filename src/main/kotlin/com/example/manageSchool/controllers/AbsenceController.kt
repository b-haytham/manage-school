package com.example.manageSchool.controllers

import com.example.manageSchool.DTOS.absence.CreateAbsenceDTO
import com.example.manageSchool.services.AbsenceServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/absences")
class AbsenceController(
        private val absenceService: AbsenceServiceImpl
) {

    @GetMapping
    fun findAll() = absenceService.findAll()

    @GetMapping("/{absenceId}")
    fun findById(@PathVariable("absenceId") absenceId: Long) = absenceService.findById(absenceId)

    @PostMapping
    fun create(@RequestBody createAbsenceDTO: CreateAbsenceDTO) = absenceService.create(createAbsenceDTO)
}