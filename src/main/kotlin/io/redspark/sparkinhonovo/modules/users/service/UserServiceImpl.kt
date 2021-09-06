package io.redspark.sparkinhonovo.modules.users.service

import io.redspark.sparkinhonovo.database.entities.Address
import io.redspark.sparkinhonovo.database.entities.Group
import io.redspark.sparkinhonovo.database.entities.Role
import io.redspark.sparkinhonovo.database.entities.User
import io.redspark.sparkinhonovo.database.repositories.AddressRepository
import io.redspark.sparkinhonovo.database.repositories.GroupRepository
import io.redspark.sparkinhonovo.database.repositories.RoleRepository
import io.redspark.sparkinhonovo.database.repositories.UserRepository
import io.redspark.sparkinhonovo.models.dtos.CreateUserDTO
import io.redspark.sparkinhonovo.models.dtos.UserDTO
import io.redspark.sparkinhonovo.models.dtos.UserListDTO
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val addressRepository: AddressRepository,
    private val groupRepository: GroupRepository,
    private val roleRepository: RoleRepository
):UserService {
    override fun getUserList(): List<UserListDTO> {
        return userRepository.findAll().map { user -> UserListDTO(user) }
    }

    override fun getUserById(id: UUID): UserDTO {
        val user = userRepository.getById(id)
        return UserDTO(user)
    }

    override fun createUser(createUserDTO: CreateUserDTO): UserDTO {
        val address = Address(createUserDTO)
        val group = Group(createUserDTO)
        val role = Role(createUserDTO)
        val userDTO = UserDTO(createUserDTO)

        val savedAddress = addressRepository.save(address)
        val savedGroup = groupRepository.save(group)
        val savedRole = roleRepository.save(role)

        val user = User( userDTO , savedAddress, savedGroup, savedRole)
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
                user.admission_date = userDTO.admission_date
                userRepository.save(user)
            }
    }

}