package com.eduardo.main.service

import com.eduardo.main.model.dto.CourseDto
import com.eduardo.main.model.form.CourseForm
import com.eduardo.main.model.mapper.CourseMapper
import com.eduardo.main.repository.CourseRepository
import com.eduardo.main.view.CourseView
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val courseMapper: CourseMapper
) {

    @Transactional
    fun createCourse(courseForm: CourseForm): CourseView {
        val course = courseMapper.formToModel(courseForm)
        courseRepository.save(course)
        return courseMapper.modelToView(course)
    }

    @Transactional
    fun updateCourse(courseForm: CourseForm): CourseView {
        val course = courseMapper.formToModel(courseForm)
        courseRepository.save(course)
        return courseMapper.modelToView(course)
    }

    @Transactional
    fun deleteCourse(id: Long) {
        courseRepository.deleteById(id)
    }

    fun fetchCourse(id: Long): CourseView? {
        val course = courseRepository.findById(id).orElse(null)
        return if (course != null) {
            courseMapper.modelToView(course)
        } else {
            null
        }
    }

    fun fetchCourseDatabase(id: Long) = courseRepository.findById(id).orElse(null)

    fun fetchAllCourses(): List<CourseView> {
        return courseRepository.findAll().map { courseMapper.modelToView(it) }
    }
}