package com.eduardo.main.model.form

import jakarta.validation.constraints.NotEmpty

data class UserForm(
    var id: Long? = null,
    @field:NotEmpty var name: String,
    @field:NotEmpty var username: String,
    @field:NotEmpty var password: String
)