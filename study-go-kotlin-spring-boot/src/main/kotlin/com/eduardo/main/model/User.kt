package com.eduardo.main.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User {
    @Column(name = "name", nullable = false)
    var name: String = ""

    @Id
    @GeneratedValue
    var id : Long? = null
}