package com.eduardo.main.model.view

import java.time.LocalDateTime


data class TopicView(
    var id: Long? = null,
    var title: String,
    var message: String,
    var date: LocalDateTime = LocalDateTime.now(),
    var course: CourseView,
    var author: UserView,
    var answers: List<AnswerView> = ArrayList(),
)