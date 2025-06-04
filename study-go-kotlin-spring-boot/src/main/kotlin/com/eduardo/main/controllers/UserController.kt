package com.eduardo.main.controllers

import com.eduardo.main.model.form.UserForm
import com.eduardo.main.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
@Tag(name = "Users API", description = "Endpoints for managing users")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/create")
    fun createUser(@RequestBody @Valid user: UserForm) = userService.createUser(user)

    @PutMapping("/update")
    fun updateUser(@RequestBody @Valid user: UserForm) = userService.updateUser(user)

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Long) = userService.deleteUser(id)

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) = userService.fetchUser(id)

    @GetMapping("/list")
    fun listUsers() = userService.fetchAllUsers()
}