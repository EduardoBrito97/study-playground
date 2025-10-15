package com.eduardo.main.model.database

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var name: String = "",
    @Column(nullable = false)
    var username: String = "",
    @Column(nullable = false)
    var password: String = "",
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role::class)
    @JoinTable(name = "users_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: List<Role> = mutableListOf(),
) : Serializable {
    constructor() : this(name = "", username = "", password = "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}
