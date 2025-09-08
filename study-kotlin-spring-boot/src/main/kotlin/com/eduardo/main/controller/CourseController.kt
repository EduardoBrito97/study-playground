package com.eduardo.main.controller

import com.eduardo.main.exception.NotFoundException
import com.eduardo.main.model.form.CourseForm
import com.eduardo.main.service.CourseService
import com.eduardo.main.model.view.CourseView
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/course")
@Tag(name = "Courses API", description = "Endpoints for managing courses")
class CourseController(
    private val courseService: CourseService
) {

    @PostMapping("/create")
    fun createCourse(
        @RequestBody @Valid course: CourseForm,
        uriBuilder: UriComponentsBuilder) : ResponseEntity<CourseView> {
        val courseView = courseService.createCourse(course)
        val uri = uriBuilder.path("/course/${courseView.id}").build().toUri()
        return ResponseEntity.created(uri).body(courseView)
    }

    @PutMapping("/update")
    fun updateCourse(@RequestBody @Valid course: CourseForm) = courseService.updateCourse(course)

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable id: Long) {
        if (courseService.fetchCourse(id) == null) {
            throw NotFoundException("course", id)
        }
        courseService.deleteCourse(id)
    }

    @GetMapping("/{id}")
    fun getCourse(@PathVariable id: Long) :CourseView {
        val course = courseService.fetchCourse(id)
        return course ?: throw NotFoundException("course", id)
    }

    @GetMapping("/list")
    fun listCourses(
        pageable: Pageable
    ) = courseService.fetchAllCourses(pageable)
}