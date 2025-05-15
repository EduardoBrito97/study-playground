package com.eduardo.main

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppStartup()

fun main(args: Array<String>) {
    runApplication<AppStartup>(*args)
}
