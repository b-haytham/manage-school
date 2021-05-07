package com.example.manageSchool.controllers

import com.example.manageSchool.DTOS.session.CreateSessionDTO
import com.example.manageSchool.services.SessionServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sessions")
class SessionController(
        private val sessionService: SessionServiceImpl
) {
    @GetMapping
    fun findAll() = sessionService.findAll()

    @GetMapping("{sessionId}")
    fun findById(@PathVariable("sessionId") sessionId: Long) = sessionService.findById(sessionId)

    @PostMapping
    fun create(@RequestBody createSessionDTO: CreateSessionDTO) = sessionService.create(createSessionDTO)
}