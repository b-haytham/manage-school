package com.example.manageSchool.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint: AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        response!!.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        val message: String

        if (authException?.cause != null) {
            message = authException.cause.toString().toString() + " " + authException.message
        } else {
            message = authException?.message.toString()
        }

        val body = ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message))

        response.outputStream.write(body)
    }
}

