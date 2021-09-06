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

    @Column(name = "admission_date")
    var admission_date: LocalDateTime,

    @OneToOne(targetEntity = Address::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    var address: Address,

    @ManyToOne(targetEntity = Group::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    var group: Group,

    @OneToOne(targetEntity = Role::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    var role: Role,

    ){

    @CreatedDate
    @Column(name = "created_date")
    lateinit var created_date: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updated_date: LocalDateTime

    constructor(userDTO: UserDTO, address: Address, group: Group, role: Role) : this(
        id = userDTO.id,
        name = userDTO.name,
        birthday = userDTO.birthday,
        email = userDTO.email,
        admission_date = userDTO.admission_date,
        address = address,
        group = group,
        role = role
    )
}
