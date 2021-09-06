package io.redspark.sparkinhonovo.database.repositories

import io.redspark.sparkinhonovo.database.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository: JpaRepository<Role, UUID> {
}