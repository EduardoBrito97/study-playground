package com.eduardo.main.model.database

import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable
import java.time.LocalDateTime

@Entity
@Table(name = "topics")
class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val message: String,

    @Column(nullable = false)
    val date: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val course: Course,

    @Column(nullable = false)
    val author: User,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    val status: TopicStatus = TopicStatus.UNANSWERED,

    @Column(nullable = false)
    @ElementCollection(targetClass = Answer::class)
    val answers: List<Answer> = ArrayList(),
) : Serializable {
    constructor() : this(title = "", message = "", course = Course(), author = User())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Topic) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}

enum class TopicStatus {
    UNANSWERED,
    NOT_SOLVED,
    CLOSED
}