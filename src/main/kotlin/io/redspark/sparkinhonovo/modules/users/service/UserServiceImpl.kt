package io.redspark.sparkinhonovo.modules.users.service

import io.redspark.sparkinhonovo.database.entities.User
import io.redspark.sparkinhonovo.database.repositories.UserRepository
import io.redspark.sparkinhonovo.models.dtos.UserDTO
import io.redspark.sparkinhonovo.models.dtos.UserListDTO
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
):UserService {
    override fun getUserList(): List<UserListDTO> {
        return userRepository.findAll().map { user -> UserListDTO(user) }
    }

    override fun getUserById(id: UUID): UserDTO {
        val user = userRepository.getById(id)
        return UserDTO(user)
    }

    override fun createUser(userDTO: UserDTO): UserDTO {
        val user = User(userDTO)
        val savedUser = userRepository.save(user)
        return UserDTO(savedUser)
    }

    override fun deleteUser(id: UUID) {
        userRepository.deleteById(id)
    }

    override fun updateUser(id: UUID, userDTO: UserDTO): Any {
       return userRepository.findById(id)
            .map { user ->
                user.name = userDTO.name
                user.email = userDTO.email
                user.birthday = userDTO.birthday
                user.state = userDTO.state
                user.admission_date = userDTO.admission_date
                userRepository.save(user)
            }
    }

}