package io.redspark.sparkinhonovo.config.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*

@Component
class JwtToken {
    @Value("\${jwt.secret}")
    private val secret: String? = null

    @Value("\${jwt.expireInSeconds}")
    private val expireInSeconds = 0

    fun generateToken(nome: String?): String {
        val dataCriacao = getDateFrom(LocalDateTime.now())
        val dataExpiracao = getDateFrom(LocalDateTime.now().plusSeconds(expireInSeconds.toLong()))
        val claims: Map<String, Any> = HashMap()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(dataCriacao)
            .setExpiration(dataExpiracao)
            .setSubject(nome)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun getUserFromToken(token: String?): String {
        val claims = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
        return claims.subject
    }

    private fun getDateFrom(now: LocalDateTime): Date {
        return Date.from(now.toInstant(OffsetDateTime.now().offset))
    }
}