package com.example.manageSchool.services

import com.example.manageSchool.repositories.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImp(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) : UserService, UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username) ?: throw  UsernameNotFoundException("Username Not Found")
        return org.springframework.security.core.userdetails.User(user.email, user.password, listOf(SimpleGrantedAuthority("ROLE_" + user.role)))
    }


}