package com.eduardo.main.model.dto

import com.eduardo.main.model.database.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    private val user: User,
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> = user.roles

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username
}
