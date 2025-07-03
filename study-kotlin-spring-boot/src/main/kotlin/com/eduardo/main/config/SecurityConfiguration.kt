package com.eduardo.main.config

import com.eduardo.main.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.core.GrantedAuthorityDefaults
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RegexRequestMatcher


@Configuration
@EnableWebSecurity
class SecurityConfiguration (
    private val userService: UserService
){
    @Bean
    // Removing the ROLE_ prefix from all roles
    fun grantedAuthorityDefaults(): GrantedAuthorityDefaults = GrantedAuthorityDefaults("")

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
        http
            // turns off CSRF protection by removing the CsrfFilter from the security filter chain. Useful for stateless applications
            .csrf { it.disable() }
            .authorizeHttpRequests {
                // Managing authorization via request path
                it
                    .requestMatchers(
                        RegexRequestMatcher.regexMatcher(".*\\/create.*"),
                        RegexRequestMatcher.regexMatcher(".*\\/edit.*"),
                        RegexRequestMatcher.regexMatcher(".*\\/delete.*"))
                        .hasRole("write")
                    .anyRequest().authenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .formLogin {
                it.disable()
            }
            .httpBasic { }
        return http.orBuild
    }

}