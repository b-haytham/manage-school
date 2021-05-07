package com.example.manageSchool.services

import com.example.manageSchool.DTOS.absence.CreateAbsenceDTO
import com.example.manageSchool.models.Absence
import com.example.manageSchool.repositories.AbsenceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class AbsenceServiceImpl(
        private val absenceRepository: AbsenceRepository,
        private val sessionService: SessionServiceImpl,
) : AbsenceService {

    @Autowired
    lateinit var studentService: StudentServiceImpl

    override fun findAll(): Iterable<Absence> = absenceRepository.findAll()

    override fun findAllById(ids: Iterable<Long>): Iterable<Absence> = absenceRepository.findAllById(ids)

    override fun findById(id: Long): Optional<Absence> = absenceRepository.findById(id)

    override fun findByStudentIdAndSubjectId(studentId: Long, subjectId: Long): Iterable<Absence> = absenceRepository.findByStudentIdAndSubjectId(studentId, subjectId)

    override fun save(absence: Absence): Absence = absenceRepository.save(absence)

    @Transactional
    override fun create(createAbsenceDTO: CreateAbsenceDTO): Absence {
        val st = studentService.findById(createAbsenceDTO.studentId)
        val se = sessionService.findById(createAbsenceDTO.sessionId)

        if (st.isEmpty || se.isEmpty) throw Exception("Bad Request")

        val student = st.get()
        val session = se.get()

        val absence = Absence()
        absence.student = student
        absence.session = session
        absence.subject = session.subject
        val savedAbsence = absenceRepository.save(absence)

        return savedAbsence
    }
}
