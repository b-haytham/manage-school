package com.example.manageSchool

import com.example.manageSchool.models.*
import com.example.manageSchool.repositories.*
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class Runner(
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository,
    private val subjectRepository: SubjectRepository,
    private val groupRepository: GroupRepository,
    private val sessionRepository: SessionRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val user1 = User(firstName = "teacher 1", lasName = "teacher 1", email = "teacher 1", password = "teacher 1", role = UserRoles.TEACHER)
        val user2 = User(firstName = "teacher 2", lasName = "teacher 2", email = "teacher 2", password = "teacher 2", role = UserRoles.TEACHER)
        val user3 = User(firstName = "teacher 3", lasName = "teacher 3", email = "teacher 3", password = "teacher 3", role = UserRoles.TEACHER)

        val teacher1 = Teacher(user = user1)
        val teacher2 = Teacher(user = user2)
        val teacher3 = Teacher(user = user3)

        val user4 = User(firstName = "student 1", lasName = "student 1", email = "student 1", password = "student 1", role = UserRoles.STUDENT)
        val user5 = User(firstName = "student 2", lasName = "student 2", email = "student 2", password = "student 2", role = UserRoles.STUDENT)
        val user6 = User(firstName = "student 3", lasName = "student 3", email = "student 3", password = "student 3", role = UserRoles.STUDENT)

        val student1 = Student(user = user4)
        val student2 = Student(user = user5)
        val student3 = Student(user = user6)

        val rteacher = teacherRepository.save(teacher1)
        val rteacher2 = teacherRepository.save(teacher2)
        val rteacher3 = teacherRepository.save(teacher3)

        studentRepository.save(student1)
        studentRepository.save(student2)
        studentRepository.save(student3)

        val subject1 = Subject(name = "math", teacher = rteacher)
        val subject2 = Subject(name = "py", teacher = rteacher2)
        val subject3 = Subject(name = "c", teacher = rteacher3)


        val rsubject =  subjectRepository.save(subject1)
        subjectRepository.save(subject2)
        subjectRepository.save(subject3)

        val group = Group(name = "Group 1", level = 1)
        val rgroup = groupRepository.save(group)

        val session = Session(date = Date(), startTime = "09:30", endTime = "10:30", group = rgroup, subject = rsubject)
        sessionRepository.save(session)

    }
}