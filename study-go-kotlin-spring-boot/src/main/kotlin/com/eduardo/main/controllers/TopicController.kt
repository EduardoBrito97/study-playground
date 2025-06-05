package com.eduardo.main.controllers

import com.eduardo.main.model.form.TopicForm
import com.eduardo.main.service.TopicService
import com.eduardo.main.view.TopicView
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
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
        uriBuilder: UriComponentsBuilder) : ResponseEntity<TopicView?> {
        val topicView = topicService.createTopic(topic)
        val uri = uriBuilder.path("/topic/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping("/update")
    fun updateTopic(@RequestBody @Valid  topic: TopicForm) = topicService.updateTopic(topic)

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTopic(@PathVariable id: Long) = topicService.deleteTopic(id)

    @GetMapping("/{id}")
    fun getTopic(@PathVariable id: Long) = topicService.fetchTopic(id)

    @GetMapping("/list")
    fun listTopics() = topicService.fetchAllTopics()
}