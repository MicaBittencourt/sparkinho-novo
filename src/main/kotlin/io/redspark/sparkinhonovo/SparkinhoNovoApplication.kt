package io.redspark.sparkinhonovo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SparkinhoNovoApplication

fun main(args: Array<String>) {
	runApplication<SparkinhoNovoApplication>(*args)
}
