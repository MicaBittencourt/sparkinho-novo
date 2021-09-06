package io.redspark.sparkinhonovo.database.repositories

import io.redspark.sparkinhonovo.database.entities.Group
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GroupRepository: JpaRepository<Group, UUID> {
}