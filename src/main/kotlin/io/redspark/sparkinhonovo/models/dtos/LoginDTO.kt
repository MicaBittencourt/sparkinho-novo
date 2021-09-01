package io.redspark.sparkinhonovo.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.sparkinhonovo.database.entities.Login
import java.util.*
import javax.persistence.Column

class LoginDTO(
    @JsonProperty("id")
    val id: UUID?,

    @JsonProperty("username")
    val username: String,

    @JsonProperty("password")
    val password: String
){
    constructor(login: Login): this(
        id = login.id,
        username = login.username,
        password = login.password
    )
}