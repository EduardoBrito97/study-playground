package com.eduardo.main.exception

import java.lang.RuntimeException

class NotFoundException(message: String?): RuntimeException(message) {
    constructor(entityName: String, id: Long) : this("No $entityName found with ID $id")
}