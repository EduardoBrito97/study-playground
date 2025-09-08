package com.eduardo.main.controller

import com.eduardo.main.exception.NotFoundException
import com.eduardo.main.model.form.TopicForm
import com.eduardo.main.service.TopicService
import com.eduardo.main.model.view.TopicView
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topic")
@Tag(name = "Topics API", description = "Endpoints for managing topics")
class TopicController(
    private val topicService: TopicService
) {

    @PostMapping("/create")
    fun createTopic(
        @RequestBody @Valid topic: TopicForm,
        uriBuilder: UriComponentsBuilder) : ResponseEntity<TopicView> {
        val topicView = topicService.createTopic(topic)
        val uri = uriBuilder.path("/topic/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping("/update")
    fun updateTopic(@RequestBody @Valid  topic: TopicForm) = topicService.updateTopic(topic)

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTopic(@PathVariable id: Long) {
        if (topicService.fetchTopic(id) == null) {
            throw NotFoundException("topic", id)
        }
        topicService.deleteTopic(id)
    }

    @GetMapping("/{id}")
    fun getTopic(@PathVariable id: Long) : TopicView {
        val topic = topicService.fetchTopic(id)
        return topic ?: throw NotFoundException("topic", id)
    }

    @GetMapping("/list")
    fun listTopics(
        pageable: Pageable
    ) = topicService.fetchAllTopics(pageable)
}