package com.eduardo.main.view

import java.time.LocalDateTime

data class AnswerView(
    var id: Long? = null,
    var message: String,
    var date: LocalDateTime = LocalDateTime.now(),
    var authorView: UserView,
    var topicView: TopicView,
)