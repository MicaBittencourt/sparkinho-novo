package io.redspark.sparkinhonovo.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class AddressDTO (
    @JsonProperty("id")
    val id: UUID?,

    @JsonProperty("city")
    val city: String,

    @JsonProperty("state")
    val state: String,
)