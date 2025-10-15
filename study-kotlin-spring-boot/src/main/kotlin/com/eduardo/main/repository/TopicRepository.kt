package com.eduardo.main.repository

import com.eduardo.main.model.database.Topic
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TopicRepository : JpaRepository<Topic, Long> {
    @Query("select topic from Topic topic where topic.course.name = :courseName")
    fun findByCourseName(@Param("courseName") courseName: String, pageable: Pageable): List<Topic>
}