package com.eduardo.main.model.form

import jakarta.validation.constraints.NotEmpty

data class CourseForm(
    var id: Long? = null,
    @field:NotEmpty var name: String,
    @field:NotEmpty var category: String,
)
