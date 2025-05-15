package com.eduardo.main.model.dto

import java.time.LocalDateTime

data class AnswerDto(
    var id: Long? = null,
    var message: String,
    var date: LocalDateTime = LocalDateTime.now(),
    var authorId: Long,
    var topicId: Long,
    var isSolver: Boolean,
)