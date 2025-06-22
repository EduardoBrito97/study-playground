package com.eduardo.main.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenAPIConfiguration {
    @Bean
    fun defineOpenApi(): OpenAPI? {
        // You can access the UI on http://localhost:8080/swagger-ui/index.html
        val server = Server()
        server.url = "http://localhost:8080"
        server.description = "Development"

        val myContact = Contact()
        myContact.name = "Eduardo B. Brito"
        myContact.email = "eduardobarreto97@gmail.com"

        val information = Info()
            .title("Forum API")
            .version("1.0")
            .description("A sample API project for managing a forum.")
            .contact(myContact)
        return OpenAPI().info(information).servers(listOf(server))
    }
}