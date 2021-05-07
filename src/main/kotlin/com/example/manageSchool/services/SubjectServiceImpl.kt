package com.example.manageSchool.services

import com.example.manageSchool.DTOS.subject.AddSubjectsToGroupsDTO
import com.example.manageSchool.DTOS.subject.CreateSubjectDTO
import com.example.manageSchool.models.Subject
import com.example.manageSchool.repositories.SubjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class SubjectServiceImpl(
        private val subjectRepository: SubjectRepository,
        private val teacherService: TeacherServiceImpl,
        private val groupService: GroupServiceImpl
) : SubjectService {

    override fun findAll(): Iterable<Subject> = subjectRepository.findAll()

    override fun findAllById(ids: Iterable<Long>): Iterable<Subject> = subjectRepository.findAllById(ids)

    override fun findById(id: Long): Optional<Subject> = subjectRepository.findById(id)

    override fun addSubjectsToGroup(addSubjectsToGroupsDTO: AddSubjectsToGroupsDTO): Iterable<Subject> {
        val group = groupService.findById(addSubjectsToGroupsDTO.groupId)
        if (group.isEmpty) throw Exception("Group Not Found")
        val subjects = this.findAllById(addSubjectsToGroupsDTO.subjectIds)

        group.get().subjects.addAll(subjects)

        subjects.forEach {
            it.groups?.add(group.get())
            subjectRepository.save(it)
        }
        return subjects
    }

    override fun create(createSubjectDTO: CreateSubjectDTO): Subject {
        val teacher = teacherService.findById(createSubjectDTO.teacherId)
        if (teacher.isEmpty) {
            throw Exception("Teacher Not Found")
        }
        val subject = Subject()
        subject.name = createSubjectDTO.name
        subject.teacher = teacher.get()
        return subjectRepository.save(subject)
    }
}