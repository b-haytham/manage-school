package com.example.manageSchool.DTOS.session

import java.util.*

data class CreateSessionDTO(
        val date: Date,
        val startTime: String,
        val endTime: String,
        val subjectId: Long,
        val groupId: Long
)
