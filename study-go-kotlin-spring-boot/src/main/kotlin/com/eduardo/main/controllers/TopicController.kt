package com.eduardo.main.controllers

import com.eduardo.main.model.form.TopicForm
import com.eduardo.main.service.TopicService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topic")
@Tag(name = "Topics API", description = "Endpoints for managing topics")
class TopicController(
    private val topicService: TopicService
) {

    @PostMapping("/create")
    fun createTopic(@RequestBody @Valid topic: TopicForm) = topicService.createTopic(topic)

    @PutMapping("/update")
    fun updateTopic(@RequestBody @Valid  topic: TopicForm) = topicService.updateTopic(topic)

    @DeleteMapping("/delete/{id}")
    fun deleteTopic(@PathVariable id: Long) = topicService.deleteTopic(id)

    @GetMapping("/{id}")
    fun getTopic(@PathVariable id: Long) = topicService.fetchTopic(id)

    @GetMapping("/list")
    fun listTopics() = topicService.fetchAllTopics()
}