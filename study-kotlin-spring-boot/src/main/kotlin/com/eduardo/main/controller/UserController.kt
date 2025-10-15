package com.eduardo.main.controller

import com.eduardo.main.exception.NotFoundException
import com.eduardo.main.model.form.UserForm
import com.eduardo.main.model.view.UserView
import com.eduardo.main.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/user")
@Tag(name = "Users API", description = "Endpoints for managing users")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/create")
    fun createUser(
        @RequestBody @Valid user: UserForm,
        uriBuilder: UriComponentsBuilder) : ResponseEntity<UserView> {
        val userView = userService.createUser(user)
        val uri = uriBuilder.path("/user/${userView.id}").build().toUri()
        return ResponseEntity.created(uri).body(userView)
    }

    @PutMapping("/update")
    fun updateUser(@RequestBody @Valid user: UserForm) = userService.updateUser(user)

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long) {
        if (userService.fetchUser(id) == null) {
            throw NotFoundException("user", id)
        }
        userService.deleteUser(id)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) : UserView {
        val user = userService.fetchUser(id)
        return user ?: throw NotFoundException("user", id)
    }

    @GetMapping("/list")
    fun listUsers(
        pageable: Pageable
    ) = userService.fetchAllUsers(pageable)
}