package com.eduardo.main.service

import com.eduardo.main.exception.NotFoundException
import com.eduardo.main.model.database.Topic
import com.eduardo.main.model.form.TopicForm
import com.eduardo.main.model.mapper.TopicMapper
import com.eduardo.main.model.view.TopicView
import com.eduardo.main.repository.TopicRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val topicRepository: TopicRepository,
    private val topicMapper: TopicMapper,
) {
    @Transactional
    fun createTopic(topicForm: TopicForm): TopicView {
        val topic = topicMapper.formToModel(topicForm)
        topicRepository.save(topic)
        return topicMapper.modelToView(topic)
    }

    @Transactional
    fun updateTopic(topicForm: TopicForm): TopicView {
        val topic = topicMapper.formToModel(topicForm)
        topicRepository.save(topic)
        return topicMapper.modelToView(topic)
    }

    @Transactional
    fun deleteTopic(id: Long) {
        topicRepository.deleteById(id)
    }

    fun fetchTopic(id: Long): TopicView? {
        val topic = topicRepository.findById(id).orElse(null)
        return if (topic != null) {
            topicMapper.modelToView(topic)
        } else {
            null
        }
    }

    fun fetchTopicDatabase(id: Long): Topic = topicRepository.findById(id).orElseThrow { NotFoundException("topic", id) }

    fun fetchAllTopics(pageable: Pageable) =
        topicRepository.findAll(pageable).map {
            topicMapper.modelToView(it)
        }

    fun fetchByCourse(
        courseName: String,
        pageable: Pageable,
    ) = topicRepository.findByCourseName(courseName, pageable).map {
        topicMapper.modelToView(it)
    }
}
