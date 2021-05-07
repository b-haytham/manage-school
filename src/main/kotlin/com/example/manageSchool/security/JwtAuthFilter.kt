package com.example.manageSchool.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthFilter(private val jwtUtils: JwtUtils) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val jwtToken = extractJwtFromRequest(request)
        if (jwtToken != null && StringUtils.hasText(jwtToken) && jwtUtils.validateToken(jwtToken)) {
            val userDetails = User(
                    jwtUtils.getUsernameFromToken(jwtToken), "",
                    listOf<SimpleGrantedAuthority>(SimpleGrantedAuthority(jwtUtils.getRolesFromToken(jwtToken)))
            )

            val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
            )
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the
            // Spring Security Configurations successfully.
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the
            // Spring Security Configurations successfully.
            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            chain.doFilter(request, response)
        } else {
            println("Cannot set the Security Context")
            chain.doFilter(request, response)
        }

    }

    fun extractJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length)
        }
        return null
    }
}