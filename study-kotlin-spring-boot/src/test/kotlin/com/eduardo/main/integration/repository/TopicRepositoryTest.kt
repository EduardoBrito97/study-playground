package com.eduardo.main.integration.repository

import com.eduardo.main.model.database.Course
import com.eduardo.main.model.database.Topic
import com.eduardo.main.model.database.User
import com.eduardo.main.repository.CourseRepository
import com.eduardo.main.repository.TopicRepository
import com.eduardo.main.repository.UserRepository
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.mysql.MySQLContainer
import kotlin.test.assertEquals

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Necessary so SpringBoot actually wires repositories
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TopicRepositoryTest {
    @Autowired
    lateinit var topicRepository: TopicRepository

    @Autowired
    lateinit var courseRepository: CourseRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun setup() {
        // ensure database is clean before each test if needed
        topicRepository.deleteAll()
        courseRepository.deleteAll()
        userRepository.deleteAll()

        val author =
            User(
                name = "User",
                username = "username",
            )
        userRepository.save(author)

        val course1 =
            Course(
                name = "Course 1",
                category = "Cat",
            )
        val course2 =
            Course(
                name = "Course 2",
                category = "Cat 2",
            )
        courseRepository.save(course1)
        courseRepository.save(course2)

        val topic1 =
            Topic(
                title = "Topic 1",
                message = "Message 1",
                course = course1,
                author = author,
            )

        val topic2 =
            Topic(
                title = "Topic 2",
                message = "Message 2",
                course = course1,
                author = author,
            )

        val topic3 =
            Topic(
                title = "Topic 3",
                message = "Message 3",
                course = course2,
                author = author,
            )

        topicRepository.save(topic1)
        topicRepository.save(topic2)
        topicRepository.save(topic3)
    }

    companion object {
        @Container
        private val mySQLContainer =
            MySQLContainer("mysql:8.0.28").apply {
                withDatabaseName("forum_test_db")
                withUsername("root_test")
                withPassword("1234")
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            // Ensure the container is started before asking for mapped ports / jdbc URL
            if (!mySQLContainer.isRunning) {
                mySQLContainer.start()
            }

            val jdbcUrl = mySQLContainer.jdbcUrl
            val username = mySQLContainer.username
            val password = mySQLContainer.password

            val flyway =
                Flyway
                    .configure()
                    .dataSource(jdbcUrl, username, password)
                    .load()
            flyway.migrate()

            registry.add("spring.datasource.url") { jdbcUrl }
            registry.add("spring.datasource.password") { password }
            registry.add("spring.datasource.username") { username }
        }
    }

    @Test
    fun `test findByCourseName should return topic by course`() {
        val topics = topicRepository.findByCourseName("Course 1", Pageable.unpaged()).toList()
        topics.forEach { assertEquals(it.course.name, "Course 1") }
    }
}
