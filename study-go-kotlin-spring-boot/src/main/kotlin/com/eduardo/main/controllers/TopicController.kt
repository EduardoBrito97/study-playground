package com.eduardo.main.controllers

import com.eduardo.main.model.form.TopicForm
import com.eduardo.main.service.TopicService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topic")
class TopicController(
    private val topicService: TopicService
) {

    @PostMapping("/create")
    fun createTopic(@RequestBody @Valid topic: TopicForm) = topicService.createTopic(topic)

    @PutMapping("/update")
    fun updateTopic(@RequestBody @Valid  topic: TopicForm) = topicService.updateTopic(topic)

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Long) = topicService.deleteTopic(id)

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) = topicService.fetchTopic(id)

    @GetMapping("/list")
    fun listTopics() = topicService.fetchAllTopics()
}