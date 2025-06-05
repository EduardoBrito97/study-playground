package com.eduardo.main.controllers

import com.eduardo.main.model.form.AnswerForm
import com.eduardo.main.service.AnswerService
import com.eduardo.main.view.AnswerView
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/answer")
@Tag(name =  "Answers API", description = "Endpoints for managing answers")
class AnswerController(
    private val answerService: AnswerService
) {

    @PostMapping("/create")
    fun createAnswer(
        @RequestBody @Valid answer: AnswerForm,
         uriBuilder: UriComponentsBuilder) : ResponseEntity<AnswerView> {
            val answerView = answerService.createAnswer(answer)
            val uri = uriBuilder.path("/answer/${answerView.id}").build().toUri()
            return ResponseEntity.created(uri).body(answerView)
    }

    @PutMapping("/update")
    fun updateAnswer(@RequestBody @Valid answer: AnswerForm) = answerService.updateAnswer(answer)

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAnswer(@PathVariable id: Long) = answerService.deleteAnswer(id)

    @GetMapping("/{id}")
    fun getAnswer(@PathVariable id: Long) = answerService.fetchAnswer(id)

    @GetMapping("/list")
    fun listAnswers(): List<AnswerView> = answerService.fetchAllAnswers()
}