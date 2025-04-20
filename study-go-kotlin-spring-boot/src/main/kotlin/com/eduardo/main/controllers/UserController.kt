package com.eduardo.main.controllers

import com.eduardo.main.model.User
import com.eduardo.main.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController (
    private val userService: UserService
) {

    @PostMapping("/add")
    fun addUser(@RequestParam(name = "name") name : String) {
        userService.addUser(name)
    }

    @GetMapping("/list")
    fun listUsers() : User {
        val user = userService.fetchAllUsers().first()
        println(user.name)
        return user
    }
}