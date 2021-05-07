package com.example.manageSchool.DTOS.student


data class AddStudentToGroupDTO(
        val groupId: Long,
        val studentIds: Set<Long>
)
