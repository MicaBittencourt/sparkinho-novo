package io.redspark.sparkinhonovo.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.sparkinhonovo.database.entities.User
import java.util.*

class UserListDTO(
    @JsonProperty("id")
    val id: UUID?,

    @JsonProperty("name")
    val name: String

) {

    constructor(user: User): this(
        id = user.id,
        name = user.name

    )

}