package com.eduardo.main.model.dto

import com.eduardo.main.model.enums.TopicStatus
import java.time.LocalDateTime

data class TopicDto(
    var id: Long? = null,
    var title: String,
    var message: String,
    var date: LocalDateTime = LocalDateTime.now(),
    var courseId: Long,
    var authorId: Long,
    var status: TopicStatus = TopicStatus.UNANSWERED,
    var answers: List<AnswerDto> = ArrayList(),
)
