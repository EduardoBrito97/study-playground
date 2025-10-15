package com.eduardo.main.config

import com.eduardo.main.model.Credentials
import com.eduardo.main.model.dto.UserDetail
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JWTUtil,
    private val objectMapper: ObjectMapper,
) : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
    ): Authentication? {
        val (username, password) = objectMapper.readValue(request?.inputStream, Credentials::class.java)
        val token = UsernamePasswordAuthenticationToken(username, password)
        return authManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?,
    ) {
        val user = authResult?.principal as UserDetail
        val username = user.username
        val password = user.password

        val token = jwtUtil.generateToken(username, password, user.authorities)
        response?.addHeader("Authorization", "$token")
    }
}
