package com.example.manageSchool.services

import com.example.manageSchool.DTOS.student.AddStudentToGroupDTO
import com.example.manageSchool.DTOS.student.RegisterStudentDTO
import com.example.manageSchool.models.Student
import com.example.manageSchool.models.User
import com.example.manageSchool.models.UserRoles
import com.example.manageSchool.repositories.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository,
    private val groupService: GroupServiceImpl
): StudentService {

    override fun findAll(): Iterable<Student> = studentRepository.findAll()

    override fun findAllById(ids: Iterable<Long>): MutableIterable<Student> = studentRepository.findAllById(ids)

    override fun findById(id: Long): Optional<Student> =  studentRepository.findById(id)

    override fun save(student: Student): Student = studentRepository.save(student)

    @Transactional
    override fun addStudentsToGroup(addStudentToGroupDTO: AddStudentToGroupDTO): Iterable<Student> {
        val group = groupService.findById(addStudentToGroupDTO.groupId)
        if(group.isEmpty) throw Exception("Group Not Found")
        val students = this.findAllById(addStudentToGroupDTO.studentIds)
        students.forEach {
            it.group = group.get()
            this.save(it)
        }
        return students
    }

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