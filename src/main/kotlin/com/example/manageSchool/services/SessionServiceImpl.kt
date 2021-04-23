package com.example.manageSchool.services

import com.example.manageSchool.DTOS.session.CreateSessionDTO
import com.example.manageSchool.models.Session
import com.example.manageSchool.repositories.SessionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.*

@Service
@Transactional
class SessionServiceImpl(
    private val sessionRepository: SessionRepository,
    private val subjectService: SubjectServiceImpl,
    private val groupService: GroupServiceImpl
): SessionService {

    override fun findAll(): Iterable<Session> = sessionRepository.findAll()

    override fun findAllById(ids: Iterable<Long>): Iterable<Session> = sessionRepository.findAllById(ids)

    override fun findById(id: Long): Optional<Session> = sessionRepository.findById(id)

    override fun save(session: Session): Session = sessionRepository.save(session)

    override fun create(createSessionDTO: CreateSessionDTO): Session {
        val group = groupService.findById(createSessionDTO.groupId)
        val subject = subjectService.findById(createSessionDTO.subjectId)
        if( group.isEmpty || subject.isEmpty) throw Exception("Bad Request")
        val session = Session()
        session.date = createSessionDTO.date
        session.startTime = createSessionDTO.startTime
        session.endTime = createSessionDTO.endTime
        session.group = group.get()
        session.subject = subject.get()
        return sessionRepository.save(session)
    }
}