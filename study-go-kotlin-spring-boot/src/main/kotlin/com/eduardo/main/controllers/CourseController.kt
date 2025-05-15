package com.eduardo.main.controllers

import com.eduardo.main.model.form.CourseForm
import com.eduardo.main.service.CourseService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/course")
class CourseController(
    private val courseService: CourseService
) {

    @PostMapping("/create")
    fun createCourse(@RequestBody @Valid course: CourseForm) = courseService.createCourse(course)

    @PutMapping("/update")
    fun updateCourse(@RequestBody @Valid course: CourseForm) = courseService.updateCourse(course)

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Long) = courseService.deleteCourse(id)

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) = courseService.fetchCourse(id)

    @GetMapping("/list")
    fun listCourses() = courseService.fetchAllCourses()
}