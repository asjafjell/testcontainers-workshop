package no.sjafjell.devex.testcontainersworkshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class TestcontainersWorkshopApplication

fun main(args: Array<String>) {
	runApplication<TestcontainersWorkshopApplication>(*args)
}
