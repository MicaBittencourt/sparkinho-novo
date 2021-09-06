package io.redspark.sparkinhonovo.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.sparkinhonovo.database.entities.User
import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.Email

class UserDTO (
    @JsonProperty("id")
    val id: UUID?,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("birthday")
    val birthday: LocalDateTime,

    @field:Email
    @JsonProperty("email")
    val email: String,

    @JsonProperty("admission_date")
    val admission_date: LocalDateTime

) {

    constructor(user: User): this(
        id = user.id,
        name = user.name,
        birthday = user.birthday,
        email = user.email,
        admission_date = user.admission_date

    )

    constructor(createUser: CreateUserDTO) : this(

        id = createUser.id,
        name = createUser.name,
        birthday = createUser.birthday,
        email = createUser.email,
        admission_date = createUser.admission_date
    )

}