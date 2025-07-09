package com.eduardo.main.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*


@Component
class JWTUtil {
    companion object {
        const val EXPIRATION = 60000L
    }

    @Value("\${jwt.secret}")
    private lateinit var key : String

    fun generateToken(username: String, password: String) : String? {
        val key = Keys.hmacShaKeyFor(key.toByteArray(StandardCharsets.UTF_8))
        return Jwts.builder()
            .subject(username)
            .expiration(Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(key)
            .compact()
    }
}