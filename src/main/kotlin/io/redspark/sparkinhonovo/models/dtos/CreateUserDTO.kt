package io.redspark.sparkinhonovo.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.Email

class CreateUserDTO(
    @JsonProperty("id")
    val id: UUID?,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("birthday")
    val birthday: LocalDateTime,

    @JsonProperty("email")
    @field:Email
    val email: String,

    @JsonProperty("admission_date")
    val admission_date: LocalDateTime,

    @JsonProperty("city")
    val city: String,

    @JsonProperty("state")
    val state: String,

    @JsonProperty("group")
    val group: String,

    @JsonProperty("role")
    val role: String
)