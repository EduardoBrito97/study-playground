package com.eduardo.main.model.mapper

import com.eduardo.main.model.dto.TopicDto
import com.eduardo.main.model.form.TopicForm
import com.eduardo.main.model.database.Topic
import com.eduardo.main.service.CourseService
import com.eduardo.main.service.UserService
import com.eduardo.main.view.TopicView
import org.springframework.stereotype.Component

@Component
class TopicMapper(
    private val courseService: CourseService,
    private val userService: UserService,
    private val answerMapper: AnswerMapper
) : Mapper<Topic, TopicDto, TopicForm, TopicView> {
    override fun dtoToModel(dto: TopicDto) = Topic(
        id = dto.id,
        title = dto.title,
        message = dto.message,
        date = dto.date,
        course = courseService.fetchCourseDatabase(dto.courseId)!!,
        author = userService.fetchUserDatabase(dto.authorId)!!,
        status = dto.status,
        answers = dto.answers.map { answer -> answerMapper.dtoToModel(answer) }
    )

    override fun modelToDto(model: Topic) = TopicDto(
        id = model.id,
        title = model.title,
        message = model.message,
        date = model.date,
        courseId = model.course.id!!,
        authorId = model.author.id!!,
        status = model.status,
        answers = model.answers.map { answer -> answerMapper.modelToDto(answer) }
    )

    override fun formToModel(form: TopicForm) = Topic(
        id = form.id,
        title = form.title,
        message = form.message,
        date = form.date,
        course = courseService.fetchCourseDatabase(form.courseId),
        author = userService.fetchUserDatabase(form.authorId),
        status = form.status,
        answers = form.answers.map { answer -> answerMapper.formToModel(answer) }
    )

    // @TODO - find a way to map answers correctly
    override fun modelToView(model: Topic) = TopicView(
        id = model.id,
        title = model.title,
        message = model.message,
        date = model.date,
        course =  courseService.fetchCourse(model.course.id!!)!!,
        author = userService.fetchUser(model.author.id!!)!!,
        answers = model.answers.map { answerMapper.modelToView(it) }
    )
}