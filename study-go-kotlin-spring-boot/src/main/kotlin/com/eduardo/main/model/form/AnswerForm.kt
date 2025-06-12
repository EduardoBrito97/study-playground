package com.eduardo.main.model.form

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class AnswerForm(
    var id: Long? = null,
    @field:NotEmpty var message: String,
    var date: LocalDateTime = LocalDateTime.now(),
    @field:NotNull var authorId: Long,
    @field:NotNull var topicId: Long,
    @field:NotNull var isSolver: Boolean,
)