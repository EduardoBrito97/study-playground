package com.eduardo.main.config

import com.eduardo.main.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*

@Component
class JWTUtil(
    private val userService: UserService,
) {
    companion object {
        const val EXPIRATION = 60000L
    }

    @Value("\${jwt.secret}")
    private lateinit var key: String

    fun generateToken(
        username: String?,
        password: String?,
        authorities: Collection<GrantedAuthority?>?,
    ): String? {
        val secretKey = Keys.hmacShaKeyFor(key.toByteArray(StandardCharsets.UTF_8))
        return Jwts
            .builder()
            .subject("$username:$password")
            .claim("role", authorities)
            .expiration(Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(secretKey)
            .compact()
    }

    fun isValid(token: String?): Boolean {
        val secretKey = Keys.hmacShaKeyFor(key.toByteArray(StandardCharsets.UTF_8))
        return try {
            Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
            true
        } catch (exception: IllegalArgumentException) {
            return false
        }
    }

    fun getAuthentication(token: String?): Authentication {
        val secretKey = Keys.hmacShaKeyFor(key.toByteArray(StandardCharsets.UTF_8))
        val usernameAndPassword =
            Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .payload.subject
        val splitIndex = usernameAndPassword.indexOf(":")
        val username = usernameAndPassword.take(splitIndex)
        val password = usernameAndPassword.substring(splitIndex)
        val user = userService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, password, user.authorities)
    }
}
