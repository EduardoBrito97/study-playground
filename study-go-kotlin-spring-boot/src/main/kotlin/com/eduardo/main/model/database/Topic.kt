package com.eduardo.main.model.database

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "topics")
data class Topic(
    @Id
    @GeneratedValue
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
    val status: TopicStatus = TopicStatus.UNANSWERED,

    @Column(nullable = false)
    val answers: List<Answer> = ArrayList(),
)

enum class TopicStatus {
    UNANSWERED,
    NOT_SOLVED,
    CLOSED
}