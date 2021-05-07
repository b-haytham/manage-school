package com.example.manageSchool.DTOS.student

data class TauxAbsenceResponse(
        val session_number: Int,
        val absence_number: Int,
        val taux: String
)