package com.eduardo.main.model.database

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var username: String = "",

    @Column(nullable = false)
    var password: String = ""
) : Serializable {
    constructor() : this(name = "", username = "", password = "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}