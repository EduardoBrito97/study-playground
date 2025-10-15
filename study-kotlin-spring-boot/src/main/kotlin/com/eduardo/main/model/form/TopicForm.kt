package com.eduardo.main.model.form

import com.eduardo.main.model.enums.TopicStatus
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class TopicForm(
    var id: Long? = null,
    @field:NotEmpty @Size(min = 5, max = 100) var title: String,
    @field:NotEmpty var message: String,
    var date: LocalDateTime = LocalDateTime.now(),
    @field:NotNull var courseId: Long,
    @field:NotNull var authorId: Long,
    var status: TopicStatus = TopicStatus.UNANSWERED,
    var answers: List<AnswerForm> = ArrayList(),
)
