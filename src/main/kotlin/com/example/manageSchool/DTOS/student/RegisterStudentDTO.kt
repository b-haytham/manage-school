package com.example.manageSchool.DTOS.student

data class RegisterStudentDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)