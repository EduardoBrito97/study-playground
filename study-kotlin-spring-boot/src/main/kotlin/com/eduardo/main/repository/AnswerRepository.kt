package com.eduardo.main.repository

import com.eduardo.main.model.database.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository : JpaRepository<Answer, Long> {
    @Query("select answer from Answer answer where answer.topic.id = :topicId")
    fun findByTopic(
        @Param("topicId") topicId: Long,
    ): List<Answer>
}
