package io.redspark.sparkinhonovo.database.repositories

import io.redspark.sparkinhonovo.database.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, UUID> {
}