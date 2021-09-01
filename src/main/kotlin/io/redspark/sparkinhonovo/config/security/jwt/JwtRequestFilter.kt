package io.redspark.sparkinhonovo.config.security.jwt

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(

    private var jwtTokenUtils: JwtToken? = null,
    private var jwtUserDetailService: JwtUserDetailService? = null

): OncePerRequestFilter() {

    fun JwtRequestFilter(jwtUserDetailService: JwtUserDetailService, jwtTokenUtils: JwtToken){
        this.jwtUserDetailService = jwtUserDetailService
        this.jwtTokenUtils = jwtTokenUtils
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestTokenHeader: String = request.getHeader("Authorization")
        var username: String? = null
        var jwtToken: String

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            jwtToken = requestTokenHeader.replace("Bearer", "")
            try {
                username = jwtTokenUtils?.getUserFromToken(jwtToken)
            } catch (illegalArgumentException: IllegalArgumentException) {
                logger.info("Erro ao fazer parse do token")
            } catch (expiredJwtException: ExpiredJwtException) {
                logger.info("Token expirado")
            }
        } else {
            logger.info("Token não encontrado ou fora do padrão Bearer")
        }
        if (username != null && SecurityContextHolder.getContext() == null){
            val userDetails = jwtUserDetailService!!.loadUserByUsername(username)
            val authenticationToken = UsernamePasswordAuthenticationToken(userDetails, userDetails.authorities)

            authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

            SecurityContextHolder.getContext().authentication = authenticationToken
        }
        filterChain.doFilter(request, response)

    }

}