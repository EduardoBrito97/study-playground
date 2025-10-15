package com.eduardo.main.model.view

import java.time.LocalDateTime

data class AnswerView(
    var id: Long? = null,
    var message: String,
    var date: LocalDateTime = LocalDateTime.now(),
    var authorId: Long,
    var topicId: Long,
)
