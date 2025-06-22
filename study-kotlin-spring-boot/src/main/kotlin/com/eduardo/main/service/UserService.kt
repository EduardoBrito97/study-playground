package com.eduardo.main.service

import com.eduardo.main.exception.NotFoundException
import com.eduardo.main.model.database.User
import com.eduardo.main.model.dto.UserDetail
import com.eduardo.main.model.form.UserForm
import com.eduardo.main.model.mapper.UserMapper
import com.eduardo.main.repository.UserRepository
import com.eduardo.main.model.view.UserView
import jakarta.transaction.Transactional
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    val userMapper: UserMapper
) : UserDetailsService {

    @Transactional
    fun createUser(userForm: UserForm): UserView {
        val user = userMapper.formToModel(userForm)
        userRepository.save(user)
        return userMapper.modelToView(user)
    }

    @Transactional
    fun updateUser(userForm: UserForm): UserView {
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

    fun fetchUserDatabase(id: Long): User = userRepository.findById(id).orElseThrow { NotFoundException("user", id) }

    fun fetchAllUsers(
        pageable: Pageable
    ) = userRepository.findAll(pageable).map { userMapper.modelToView(it) }

    override fun loadUserByUsername(username: String?): UserDetails? {
        val user = userRepository.findByUsername(username)
        return UserDetail(user)
    }
}