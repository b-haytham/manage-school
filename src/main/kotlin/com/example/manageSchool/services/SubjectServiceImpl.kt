package com.example.manageSchool.services

import com.example.manageSchool.DTOS.subject.CreateSubjectDTO
import com.example.manageSchool.models.Subject
import com.example.manageSchool.repositories.SubjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SubjectServiceImpl(
    private val subjectRepository: SubjectRepository,
    private val teacherService: TeacherServiceImpl
) : SubjectService {

    override fun findAll(): Iterable<Subject> = subjectRepository.findAll()

    override fun findById(id: Long): Optional<Subject> = subjectRepository.findById(id)

    override fun create(createSubjectDTO: CreateSubjectDTO): Subject {
        val teacher =  teacherService.findById(createSubjectDTO.teacherId)
        if (teacher.isEmpty) {
            throw Exception("Teacher Not Found")
        }
        val subject = Subject()
        subject.name = createSubjectDTO.name
        subject.teacher = teacher.get()
        return  subjectRepository.save(subject)
    }
}