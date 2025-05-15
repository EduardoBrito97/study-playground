package com.eduardo.main.service

import com.eduardo.main.model.form.UserForm
import com.eduardo.main.model.mapper.UserMapper
import com.eduardo.main.repository.UserRepository
import com.eduardo.main.view.UserView
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    val userMapper: UserMapper
) {

    @Transactional
    fun createUser(userForm: UserForm): UserView {
        val user = userMapper.formToModel(userForm)
        userRepository.save(user)
        return userMapper.modelToView(user)
    }

    @Transactional
    fun updateUser(userForm: UserForm) : UserView {
        val user = userMapper.formToModel(userForm)
        userRepository.save(user)
        return userMapper.modelToView(user)
    }

    @Transactional
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    fun fetchUser(id: Long): UserView? {
        val user = userRepository.findById(id).orElse(null)
        return if (user != null) {
            userMapper.modelToView(user)
        } else {
            null
        }
    }

    fun fetchUserDatabase(id: Long) = userRepository.findById(id).orElse(null)

    fun fetchAllUsers(): List<UserView> {
        return userRepository.findAll().map { userMapper.modelToView(it) }
    }
}