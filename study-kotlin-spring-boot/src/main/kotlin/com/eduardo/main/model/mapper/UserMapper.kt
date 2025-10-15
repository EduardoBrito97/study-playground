package com.eduardo.main.model.mapper

import com.eduardo.main.model.database.User
import com.eduardo.main.model.dto.UserDto
import com.eduardo.main.model.form.UserForm
import com.eduardo.main.model.view.UserView
import org.springframework.stereotype.Component

@Component
class UserMapper() : Mapper<User, UserDto, UserForm, UserView> {

    override fun dtoToModel(dto: UserDto) = User(
        id = dto.id,
        name = dto.name,
        username = dto.username,
        password = dto.password,
    )

    override fun modelToDto(model: User) = UserDto(
        id = model.id,
        name = model.name,
        username = model.username,
        password = model.password,
    )

    override fun formToModel(form: UserForm) = User(
        id = form.id,
        name = form.name,
        username = form.username,
        password = form.password,
    )

    override fun modelToView(model: User) = UserView(
        id = model.id,
        name = model.name,
        username = model.username,
        password = model.password,
    )
}