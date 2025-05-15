package com.eduardo.main.model.form

import jakarta.validation.constraints.NotEmpty
import java.time.LocalDateTime

data class AnswerForm(
    var id: Long? = null,
    @field:NotEmpty var message: String,
    @field:NotEmpty var date: LocalDateTime = LocalDateTime.now(),
    @field:NotEmpty var authorId: Long,
    @field:NotEmpty var topicId: Long,
    @field:NotEmpty var isSolver: Boolean,
)