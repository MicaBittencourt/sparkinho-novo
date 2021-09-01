package io.redspark.sparkinhonovo.modules.users.controllers

import io.redspark.sparkinhonovo.models.dtos.UserDTO
import io.redspark.sparkinhonovo.models.dtos.UserListDTO
import io.redspark.sparkinhonovo.modules.users.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController (
    private val userService: UserService
        ){
    @GetMapping
    fun getUserList():List<UserListDTO> = userService.getUserList()

    @GetMapping("{id}")
    fun getUserById(@PathVariable id: UUID):UserDTO{
        return userService.getUserById(id)
    }

    @PostMapping
    fun createUser(@RequestBody @Valid userDTO: UserDTO):UserDTO{
        return userService.createUser(userDTO)
    }

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: UUID){
        return userService.deleteUser(id)
    }

    @PutMapping("{id}")
    fun updatUser(@PathVariable id:UUID, @RequestBody userDTO: UserDTO) = userService.updateUser(id, userDTO)

}