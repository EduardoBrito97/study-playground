package com.eduardo.main.config

import com.eduardo.main.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfiguration (
    private val userService: UserService
){
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        val authBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authBuilder
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder())
        return authBuilder.build()
    }

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        http.invoke {
            // Setting all requests to require authentication
            authorizeHttpRequests {
                authorize(anyRequest, authenticated)
            }
            // Saying our app should not keep any auth info
            sessionManagement {
                SessionCreationPolicy.STATELESS
            }
            // We're allowing only http auth
            formLogin {
                disable()
            }
            // Allowing basic auth
            httpBasic {}
        }
        return http.orBuild
    }

}