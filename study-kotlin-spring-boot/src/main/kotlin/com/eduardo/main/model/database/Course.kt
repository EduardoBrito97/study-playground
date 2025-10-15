package com.eduardo.main.model.database

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "courses")
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val category: String,
) : Serializable {
    constructor() : this(name = "", category = "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Course) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}
