package com.eduardo.main.model.database

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    val name: String = "",

    @Column(nullable = false)
    val username: String = "",

    @Column(nullable = false)
    val password: String = ""
)