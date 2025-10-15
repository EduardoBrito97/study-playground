package com.eduardo.main.model.mapper

import com.eduardo.main.model.database.Course
import com.eduardo.main.model.dto.CourseDto
import com.eduardo.main.model.form.CourseForm
import com.eduardo.main.model.view.CourseView
import org.springframework.stereotype.Component

@Component
class CourseMapper : Mapper<Course, CourseDto, CourseForm, CourseView> {
    override fun dtoToModel(dto: CourseDto) =
        Course(
            id = dto.id,
            name = dto.name,
            category = dto.category,
        )

    override fun modelToDto(model: Course) =
        CourseDto(
            id = model.id,
            name = model.name,
            category = model.category,
        )

    override fun formToModel(form: CourseForm) =
        Course(
            id = form.id,
            name = form.name,
            category = form.category,
        )

    override fun modelToView(model: Course) =
        CourseView(
            id = model.id,
            name = model.name,
            category = model.category,
        )
}
