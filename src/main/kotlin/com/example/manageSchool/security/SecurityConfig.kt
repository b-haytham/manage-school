package com.example.manageSchool.security

import com.example.manageSchool.services.UserServiceImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val userService: UserServiceImp, private val passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var jwtAuthFilter: JwtAuthFilter

    @Autowired
    lateinit var jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint

    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/users/register",
                        "/api/users/login",
                        "/h2-console",
                        "/api/teachers/register",
                        "/api/students/register",
                        "/api/subjects/",
                        "/api/teachers/",
                        "/api/students/**",
                        "/api/users/",
                        "/api/groups/**",
                ).permitAll()
                .antMatchers("/api/users/only-admin").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/api/users/only-prof").hasAnyAuthority("ROLE_PROF")
                .anyRequest().permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userService).passwordEncoder(passwordEncoder)
    }


    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}