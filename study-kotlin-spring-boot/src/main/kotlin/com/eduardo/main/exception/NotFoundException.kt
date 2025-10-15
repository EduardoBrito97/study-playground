package com.eduardo.main.exception

class NotFoundException(
    message: String?,
) : RuntimeException(message) {
    constructor(entityName: String, id: Long) : this("No $entityName found with ID $id")
}
