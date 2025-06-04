package com.eduardo.main.controllers

import com.eduardo.main.model.form.CourseForm
import com.eduardo.main.service.CourseService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/course")
@Tag(name = "Courses API", description = "Endpoints for managing courses")
class CourseController(
    private val courseService: CourseService
) {

    @PostMapping("/create")
    fun createCourse(@RequestBody @Valid course: CourseForm) = courseService.createCourse(course)

    @PutMapping("/update")
    fun updateCourse(@RequestBody @Valid course: CourseForm) = courseService.updateCourse(course)

    @DeleteMapping("/delete/{id}")
    fun deleteCourse(@PathVariable id: Long) = courseService.deleteCourse(id)

    @GetMapping("/{id}")
    fun getCourse(@PathVariable id: Long) = courseService.fetchCourse(id)

    @GetMapping("/list")
    fun listCourses() = courseService.fetchAllCourses()
}