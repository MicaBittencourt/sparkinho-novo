package io.redspark.sparkinhonovo.database.entities

import io.redspark.sparkinhonovo.models.dtos.CreateUserDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "address")
data class Address(
    @Id
    @Column(name = "address_id")
    @GeneratedValue
    val id: UUID? = null,

    @Column(name = "city")
    var city: String,

    @Column(name = "state")
    var state: String
){
    constructor(createUserDTO: CreateUserDTO) : this(
//        id = createUserDTO.id,
        city = createUserDTO.city,
        state = createUserDTO.state
    )

    @CreatedDate
    @Column(name = "created_date")
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime
}