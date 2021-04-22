package com.example.manageSchool.services

import com.example.manageSchool.DTOS.student.RegisterStudentDTO
import com.example.manageSchool.models.Student
import com.example.manageSchool.models.User
import com.example.manageSchool.models.UserRoles
import com.example.manageSchool.repositories.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentServiceImpl(
    private var studentRepository: StudentRepository
): StudentService {

    override fun findAll(): Iterable<Student> = studentRepository.findAll()

    override fun findById(id: Long): Optional<Student> =  studentRepository.findById(id)

    override fun register(registerStudentDTO: RegisterStudentDTO): Student {
        val user = User()
        user.firstName = registerStudentDTO.firstName
        user.lasName = registerStudentDTO.lastName
        user.email = registerStudentDTO.email
        user.password = registerStudentDTO.password
        user.role = UserRoles.STUDENT

        val student = Student()
        student.user = user

        return  studentRepository.save(student)
    }
}