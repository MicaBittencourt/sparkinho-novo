package io.redspark.sparkinhonovo.modules.users.service

import io.redspark.sparkinhonovo.models.dtos.CreateUserDTO
import io.redspark.sparkinhonovo.models.dtos.UserDTO
import io.redspark.sparkinhonovo.models.dtos.UserListDTO
import java.util.*

interface UserService {
    fun getUserList(): List<UserListDTO>
    fun getUserById(id: UUID): UserDTO
    fun createUser(createUserDTO: CreateUserDTO): UserDTO
    fun deleteUser(id: UUID)
    fun updateUser(id: UUID, userDTO: UserDTO): Any

}
