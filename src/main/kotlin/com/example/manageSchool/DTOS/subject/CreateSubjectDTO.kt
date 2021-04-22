package com.example.manageSchool.DTOS.subject

data class CreateSubjectDTO(
    val name: String,
    val teacherId: Long
)