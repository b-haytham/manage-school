package com.example.manageSchool.security;

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtUtils(
        @Value("\${jwt.secret}") private val jwtSecret: String,
        @Value("\${jwt.expirationDateInMs}") private val jwtEpirationDate: Int
) {
    fun generateToken(userDetails: UserDetails): String? {
        val claims = mutableMapOf<String, String>()
        val roles = userDetails.authorities
        when {
            roles.contains(SimpleGrantedAuthority("ROLE_ADMIN")) -> {
                claims["role"] = "ROLE_ADMIN"
            }
            roles.contains(SimpleGrantedAuthority("ROLE_PROF")) -> {
                claims["role"] = "ROLE_PROF"
            }
            else -> {
                claims["role"] = "ROLE_STUDENT"
            }
        }
        return Jwts.builder()
                .setClaims(claims as Map<String, Any>?)
                .setSubject(userDetails.username)
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + jwtEpirationDate))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            return true
        } catch (ex: Exception) {
            throw  BadCredentialsException("Invalid Credentials")
        }
    }

    fun getUsernameFromToken(token: String?): String? {
        val claims: Claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
        return claims.subject
    }

    fun getRolesFromToken(authToken: String?): String {
        val claims: Claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).body
        return claims["role"] as String
    }
}