package com.eduardo.main.controllers

import com.eduardo.main.model.form.AnswerForm
import com.eduardo.main.service.AnswerService
import com.eduardo.main.view.AnswerView
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/answer")
class AnswerController(
    private val answerService: AnswerService
) {

    @PostMapping("/create")
    fun createAnswer(@RequestBody @Valid answer: AnswerForm) = answerService.createAnswer(answer)

    @PutMapping("/update")
    fun updateAnswer(@RequestBody @Valid answer: AnswerForm) = answerService.updateAnswer(answer)

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Long) = answerService.deleteAnswer(id)

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) = answerService.fetchAnswer(id)

    @GetMapping("/list")
    fun listAnswers(): List<AnswerView> = answerService.fetchAllAnswers()
}