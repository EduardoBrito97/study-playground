package com.eduardo.main.service

import com.eduardo.main.model.User
import com.eduardo.main.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository) {

    @Transactional
    fun addUser(name: String) {
        val user = User()
        user.name = name

        userRepository.save(user)
    }

    fun fetchUser(id: Int): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun fetchAllUsers(): List<User> {
        return userRepository.findAll()
    }
}