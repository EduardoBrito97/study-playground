package com.eduardo.main.repository

import com.eduardo.main.model.database.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository : JpaRepository<Answer, Long> {
    fun findByTopic(topicId: Long): List<Answer>
}