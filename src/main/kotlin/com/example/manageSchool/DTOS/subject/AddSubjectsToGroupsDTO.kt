package com.example.manageSchool.DTOS.subject


data class AddSubjectsToGroupsDTO(
        val subjectIds: Set<Long>,
        val groupId: Long
)
