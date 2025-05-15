package com.eduardo.main.repository

import com.eduardo.main.model.database.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Long>