package io.redspark.sparkinhonovo.database.entities

import io.redspark.sparkinhonovo.models.dtos.LoginDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "TB_LOGIN")
data class Login(

        @Id
        @GeneratedValue
        @Column(name = "login_id")
        val id: UUID? = null,

        @Column(name = "username")
        val username: String,

        @Column(name = "password")
        val password: String

){

        constructor(loginDTO: LoginDTO) : this(
                id = loginDTO.id,
                username = loginDTO.username,
                password = loginDTO.password,

                )

        @CreatedDate
        @Column(name = "created_date")
        lateinit var createdDate: LocalDateTime

        @LastModifiedDate
        @Column(name = "updated_date")
        lateinit var updatedDate: LocalDateTime

}