package com.eduardo.main.model.database

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime

@Entity
@Table(name = "answers")
class Answer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val message: String,

    @Column(nullable = false)
    val date: LocalDateTime = LocalDateTime.now(),

    @JoinColumn(name = "user_id")
    @ManyToOne
    val author: User,

    @JoinColumn(name = "topic_id")
    @ManyToOne
    val topic: Topic,

    @Column(nullable = false)
    val isSolver: Boolean,
) : Serializable {
    constructor() : this(message = "", author = User(), topic = Topic(), isSolver = false)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Answer) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}
