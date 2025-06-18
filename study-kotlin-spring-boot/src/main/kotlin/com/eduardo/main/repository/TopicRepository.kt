package com.eduardo.main.repository

import com.eduardo.main.model.database.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicRepository : JpaRepository<Topic, Long>