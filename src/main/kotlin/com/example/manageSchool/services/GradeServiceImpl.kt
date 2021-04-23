package com.example.manageSchool.services

import com.example.manageSchool.DTOS.grade.CreateGradeDTO
import com.example.manageSchool.DTOS.grade.PatchDsDTO
import com.example.manageSchool.models.Grade
import com.example.manageSchool.repositories.GradeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class GradeServiceImpl(
    private val gradeRepository: GradeRepository,
    private val studentService: StudentServiceImpl,
    private val subjectService: SubjectServiceImpl
): GradeService {

    override fun findAll(): Iterable<Grade> = gradeRepository.findAll()
    override fun findAllById(ids: Iterable<Long>): Iterable<Grade> = gradeRepository.findAllById(ids)
    override fun findById(id: Long): Grade {
        val grade = gradeRepository.findById(id)
        if(grade.isEmpty) throw Exception("Grade Not Found")
        return grade.get()
    }

    override fun save(grade: Grade): Grade = gradeRepository.save(grade)

    override fun patchDs(gradeId: Long, patchDsDTO: PatchDsDTO): Grade {
        val grade = this.findById(gradeId)
        grade.ds = patchDsDTO.ds
        return gradeRepository.save(grade)
    }

    override fun create(createGradeDTO: CreateGradeDTO): Grade {
        val st = studentService.findById(createGradeDTO.studentId)
        val su = subjectService.findById(createGradeDTO.subjectId)
        if(st.isEmpty || su.isEmpty) throw  Exception(">>>> Bad Request || ")

        val student = st.get()
        val subject = su.get()

        val grade = Grade()
        grade.student = student
        grade.subject = subject

        return  gradeRepository.save(grade)
    }
}