package com.eduardo.main.model.database

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.NamedQuery
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "answers")
@NamedQuery(name = "Answer.findByTopic", query = "select a from Answer a where a.topic.id = ?1")
data class Answer(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    val message: String,

    @Column(nullable = false)
    val date: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val author: User,

    @Column(nullable = false)
    val topic: Topic,

    @Column(nullable = false)
    val isSolver: Boolean,
)
