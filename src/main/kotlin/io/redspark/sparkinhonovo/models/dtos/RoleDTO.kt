package io.redspark.sparkinhonovo.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class RoleDTO (
    @JsonProperty("id")
    val id: UUID?,

    @JsonProperty("name")
    val name: String,

)