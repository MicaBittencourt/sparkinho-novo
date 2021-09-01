package io.redspark.sparkinhonovo.database.repositories

import io.redspark.sparkinhonovo.database.entities.Login
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LoginRepository:JpaRepository<Login, UUID> {

    fun findFirstByUsername(username: String?): Login?
}