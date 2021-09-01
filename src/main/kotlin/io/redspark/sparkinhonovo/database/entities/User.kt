package io.redspark.sparkinhonovo.database.entities

import io.redspark.sparkinhonovo.models.dtos.UserDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "proj_user")
data class User(

    @Id
    @Column(name = "user_id")
    @GeneratedValue
    val id: UUID? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "birthday")
    var birthday: LocalDateTime,

    @Column(name = "email")
    var email: String,

    @Column(name = "state")
    var state: String,

    @Column(name = "admission_date")
    var admission_date: LocalDateTime,

    ){

    @CreatedDate
    @Column(name = "created_date")
    lateinit var created_date: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updated_date: LocalDateTime

    constructor(userDto: UserDTO): this(
        id = userDto.id,
        name = userDto.name,
        birthday = userDto.birthday,
        email = userDto.email,
        state = userDto.state,
        admission_date = userDto.admission_date
    )
}
