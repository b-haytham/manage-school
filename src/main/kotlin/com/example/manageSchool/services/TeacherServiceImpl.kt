package com.example.manageSchool.services

import com.example.manageSchool.DTOS.teacher.RegisterTeacherDTO
import com.example.manageSchool.models.Teacher
import com.example.manageSchool.models.User
import com.example.manageSchool.models.UserRoles
import com.example.manageSchool.repositories.TeacherRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TeacherServiceImpl(
    private val teacherRepository: TeacherRepository
): TeacherService {

    override fun findAll(): Iterable<Teacher> = teacherRepository.findAll()

    override fun findById(id: Long): Optional<Teacher> = teacherRepository.findById(id)

    override fun register(registerTeacherDTO: RegisterTeacherDTO): Teacher {
        val user = User()
        user.firstName = registerTeacherDTO.firstName
        user.lasName = registerTeacherDTO.lastName
        user.email = registerTeacherDTO.email
        user.password = registerTeacherDTO.password
        user.role = UserRoles.TEACHER

        val teacher = Teacher()
        teacher.user = user
        return teacherRepository.save(teacher)
    }
}