package com.eduardo.main.model.database

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "roles")
class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    val name: String = ""
) : GrantedAuthority {
    constructor() : this(null, "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Role) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0

    override fun getAuthority(): String = name
}