package com.eduardo.main.service

import com.eduardo.main.model.database.Topic
import com.eduardo.main.model.mapper.TopicMapper
import com.eduardo.main.model.view.TopicView
import com.eduardo.main.repository.TopicRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.springframework.data.domain.Pageable
import kotlin.test.Test

/**
 * This is a sample test class for TopicService.
 * The idea here is to help and guide you how to write tests for Spring using Mockk and AssertJ.
 * */
class TestTopicService {
    val topicRepository: TopicRepository = mockk()
    val topicMapper: TopicMapper = mockk()
    val topicService: TopicService = TopicService(topicRepository, topicMapper)
    val pageable: Pageable = mockk()

    @Test
    fun `test fetch topic by course should return a list of topic by course`() {
        // given
        val courseName = "Test course"
        val topicList = listOf(mockk<Topic>(), mockk<Topic>())
        val topicViewList = listOf(mockk<TopicView>(), mockk<TopicView>())

        every { topicRepository.findByCourseName(courseName, pageable) } returns topicList
        every { topicMapper.modelToView(any()) } returnsMany topicViewList

        // when
        val result = topicService.fetchByCourse(courseName, pageable)

        // then
        assertThat(result).isEqualTo(topicViewList)
        verify(exactly = 1) { topicRepository.findByCourseName(courseName, pageable) }
        verify(exactly = topicList.size) { topicMapper.modelToView(any()) }
    }
}