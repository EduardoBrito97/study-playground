package com.eduardo.main.service

import com.eduardo.main.exception.NotFoundException
import com.eduardo.main.model.database.Answer
import com.eduardo.main.model.form.AnswerForm
import com.eduardo.main.model.mapper.AnswerMapper
import com.eduardo.main.repository.AnswerRepository
import com.eduardo.main.model.view.AnswerView
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val answerMapper: AnswerMapper
) {

    @Transactional
    fun createAnswer(answerForm: AnswerForm): AnswerView {
        val answer = answerMapper.formToModel(answerForm)
        answerRepository.save(answer)
        return answerMapper.modelToView(answer)
    }

    @Transactional
    fun updateAnswer(answerForm: AnswerForm): AnswerView {
        val answer = answerMapper.formToModel(answerForm)
        answerRepository.save(answer)
        return answerMapper.modelToView(answer)
    }

    @Transactional
    fun deleteAnswer(id: Long) {
        answerRepository.deleteById(id)
    }

    fun fetchAnswer(id: Long): AnswerView? {
        val answer = answerRepository.findById(id).orElse(null)
        return if (answer != null) {
            answerMapper.modelToView(answer)
        } else {
            null
        }
    }

    fun fetchAnswerDatabase(id: Long) = answerRepository.findById(id).orElseThrow { NotFoundException("answer", id) }

    fun fetchAllAnswers(
        pageable: Pageable
    ) = answerRepository.findAll(pageable).map { answerMapper.modelToView(it) }

    fun fetchAnswerFromTopic(topicId: Long?): List<AnswerView> {
        return if (topicId == null) {
            listOf()
        } else {
            answerRepository.findByTopic(topicId).map { answerMapper.modelToView(it) }.toList()
        }
    }

    fun fetchAnswerFromTopicEntity(topicId: Long?): List<Answer> {
        return if (topicId == null) {
            listOf()
        } else {
            answerRepository.findByTopic(topicId)
        }
    }
}