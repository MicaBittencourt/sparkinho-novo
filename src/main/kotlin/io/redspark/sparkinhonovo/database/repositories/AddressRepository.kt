package io.redspark.sparkinhonovo.database.repositories

import io.redspark.sparkinhonovo.database.entities.Address
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AddressRepository: JpaRepository<Address, UUID> {
}