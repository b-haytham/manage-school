package com.example.manageSchool.services

import com.example.manageSchool.DTOS.student.AddStudentToGroupDTO
import com.example.manageSchool.DTOS.student.RegisterStudentDTO
import com.example.manageSchool.DTOS.student.TauxAbsenceResponse
import com.example.manageSchool.models.Student
import com.example.manageSchool.models.User
import com.example.manageSchool.models.UserRoles
import com.example.manageSchool.repositories.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.util.*

@Service
@Transactional
class StudentServiceImpl(
        private val studentRepository: StudentRepository,
        private val groupService: GroupServiceImpl,
        private val sessionService: SessionServiceImpl
) : StudentService {

    @Autowired
    lateinit var absenceService: AbsenceServiceImpl

    override fun findAll(): Iterable<Student> = studentRepository.findAll()

    override fun findAllById(ids: Iterable<Long>): MutableIterable<Student> = studentRepository.findAllById(ids)

    override fun findById(id: Long): Optional<Student> = studentRepository.findById(id)

    override fun tauxAbsence(studentId: Long, subjectId: Long): TauxAbsenceResponse {
        val student = this.findById(studentId)
        if(student.isEmpty) throw RuntimeException("Student Not Found")
        val absenceList = absenceService.findByStudentIdAndSubjectId(studentId, subjectId)
        val sessionList = sessionService.findByGroupIdAndSubjectId(student.get().group!!.id!!, subjectId)
        println(absenceList.count())
        println(sessionList.count())
        return  TauxAbsenceResponse(sessionList.count(), absenceList.count(), "${absenceList.count().toDouble().div(sessionList.count().toDouble()) * 100}%")
    }

    override fun save(student: Student): Student = studentRepository.save(student)


    override fun addStudentsToGroup(addStudentToGroupDTO: AddStudentToGroupDTO): Iterable<Student> {
        val g = groupService.findById(addStudentToGroupDTO.groupId)
        if (g.isEmpty) throw Exception("Group Not Found")
        val students = this.findAllById(addStudentToGroupDTO.studentIds)

        val group = g.get()

        group.students?.addAll(students)
        groupService.save(group)
        students.forEach {
            it.group = group
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

        return studentRepository.save(student)
    }
}