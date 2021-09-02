package io.redspark.sparkinhonovo.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.redspark.sparkinhonovo.database.entities.Login

class AuthDTO (
    @JsonProperty("username")
    val username: String,

    @JsonProperty("password")
    val password: String
)