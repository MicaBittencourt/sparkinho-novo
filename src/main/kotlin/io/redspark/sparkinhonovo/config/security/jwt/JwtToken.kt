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
class JwtToken(

    @Value("\${jwt.secret}")
    private var secret: String? = null,

    @Value("\${jwt.expireInSeconds}")
    private val expireInSeconds: Int = 0
) {
    fun getDateFrom(now: LocalDateTime): Date {
        return Date.from(now.toInstant((OffsetDateTime.now().offset)))
    }

    fun generateToken(nome: String?): String? {
        val dataCriacao: Date = getDateFrom(LocalDateTime.now())
        val dataExpiracao: Date = getDateFrom(LocalDateTime.now().plusSeconds(expireInSeconds.toLong()))
        return Jwts.builder()
            .setIssuedAt(dataCriacao)
            .setExpiration(dataExpiracao)
            .setSubject(nome)
            .setClaims(HashMap())
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun getUserFromToken(token: String?): String? {
        val claims: Claims = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
        return claims.subject
    }



}

