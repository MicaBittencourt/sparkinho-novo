package io.redspark.sparkinhonovo.config.security.jwt

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.logging.Logger
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(
    jwtTokenUtil: JwtToken,
    jwtUserDetailService: JwtUserDetailService
) : OncePerRequestFilter() {
    private val logger = Logger.getLogger(JwtRequestFilter::class.java.name)
    private val jwtTokenUtil: JwtToken
    private val jwtUserDetailService: JwtUserDetailService

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeaderToken = request.getHeader("Authorization")
        var nome: String? = null
        val jwtToken: String
        if (authHeaderToken != null && authHeaderToken.startsWith(BEARER)) {
            jwtToken = authHeaderToken.replace(BEARER, "")
            try {
                nome = jwtTokenUtil.getUserFromToken(jwtToken)
            } catch (illegalArgumentException: IllegalArgumentException) {
                logger.info("Erro ao fazer parse do token")
            } catch (expiredJwtException: ExpiredJwtException) {
                logger.info("Token expirado")
            }
        } else {
            logger.info("Token não encontrado ou fora do padrão Bearer")
        }
        if (nome != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = jwtUserDetailService.loadUserByUsername(nome)
            val authenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authenticationToken
        }
        filterChain.doFilter(request, response)
    }

    companion object {
        const val BEARER = "Bearer "
    }

    init {
        this.jwtTokenUtil = jwtTokenUtil
        this.jwtUserDetailService = jwtUserDetailService
    }
}