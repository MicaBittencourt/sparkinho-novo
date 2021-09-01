package io.redspark.sparkinhonovo.config.security.jwt

import io.redspark.sparkinhonovo.database.entities.Login
import io.redspark.sparkinhonovo.database.repositories.LoginRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailService(
    private var loginRepository: LoginRepository
) : UserDetailsService {


    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val usuario: Login = loginRepository.findFirstByUsername(username)
            ?: throw UsernameNotFoundException("Usuário não encontrado: $username")
        return User(
            usuario.username,
            usuario.password,
            ArrayList<GrantedAuthority>() // Roles
        )
    }

    init {
        this.loginRepository = loginRepository
    }
}