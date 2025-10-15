package com.eduardo.main.controller

import com.eduardo.main.exception.NotFoundException
import com.eduardo.main.model.form.AnswerForm
import com.eduardo.main.model.view.AnswerView
import com.eduardo.main.service.AnswerService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/answer")
@Tag(name = "Answers API", description = "Endpoints for managing answers")
class AnswerController(
    private val answerService: AnswerService,
) {
    @PostMapping("/create")
    fun createAnswer(
        @RequestBody @Valid answer: AnswerForm,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<AnswerView> {
        val answerView = answerService.createAnswer(answer)
        val uri = uriBuilder.path("/answer/${answerView.id}").build().toUri()
        return ResponseEntity.created(uri).body(answerView)
    }

    @PutMapping("/update")
    fun updateAnswer(
        @RequestBody @Valid answer: AnswerForm,
    ) = answerService.updateAnswer(answer)

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAnswer(
        @PathVariable id: Long,
    ) {
        if (answerService.fetchAnswer(id) == null) {
            throw NotFoundException("answer", id)
        }
        answerService.deleteAnswer(id)
    }

    @GetMapping("/{id}")
    fun getAnswer(
        @PathVariable id: Long,
    ): AnswerView {
        val answer = answerService.fetchAnswer(id)
        return answer ?: throw NotFoundException("answer", id)
    }

    @GetMapping("/list")
    fun listAnswers(pageable: Pageable) = answerService.fetchAllAnswers(pageable)
}
