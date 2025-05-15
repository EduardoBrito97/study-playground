package com.eduardo.main.model.mapper

interface Mapper<Model, Dto, Form, View> {
    fun dtoToModel(dto: Dto): Model
    fun formToModel(form: Form): Model
    fun modelToDto(model: Model): Dto
    fun modelToView(model: Model): View
}