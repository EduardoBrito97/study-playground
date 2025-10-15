package com.eduardo.main.model.mapper

import com.eduardo.main.model.database.Answer
import com.eduardo.main.model.dto.AnswerDto
import com.eduardo.main.model.form.AnswerForm
import com.eduardo.main.model.view.AnswerView
import com.eduardo.main.service.TopicService
import com.eduardo.main.service.UserService
import org.springframework.stereotype.Component

@Component
class AnswerMapper(
    private val userService: UserService,
    private val topicService: TopicService,
) : Mapper<Answer, AnswerDto, AnswerForm, AnswerView> {
    override fun dtoToModel(dto: AnswerDto) =
        Answer(
            id = dto.id,
            message = dto.message,
            date = dto.date,
            author = userService.fetchUserDatabase(dto.authorId),
            topic = topicService.fetchTopicDatabase(dto.topicId),
            isSolver = dto.isSolver,
        )

    override fun modelToDto(model: Answer) =
        AnswerDto(
            id = model.id,
            message = model.message,
            date = model.date,
            authorId = model.author.id!!,
            topicId = model.topic.id!!,
            isSolver = model.isSolver,
        )

    override fun formToModel(form: AnswerForm) =
        Answer(
            id = form.id,
            message = form.message,
            date = form.date,
            author = userService.fetchUserDatabase(form.authorId),
            topic = topicService.fetchTopicDatabase(form.topicId),
            isSolver = form.isSolver,
        )

    override fun modelToView(model: Answer) =
        AnswerView(
            id = model.id,
            message = model.message,
            date = model.date,
            authorId = model.author.id!!,
            topicId = model.topic.id!!,
        )
}
