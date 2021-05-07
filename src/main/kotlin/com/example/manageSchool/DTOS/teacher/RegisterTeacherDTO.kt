package com.example.manageSchool.DTOS.teacher

data class RegisterTeacherDTO(
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String
)