package com.testTask.app

import com.testTask.database.DAO
import com.testTask.interfaces.DBConnection
import com.testTask.services.StatusService
import com.testTask.services.TicketService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class AppApplication

val connection: DBConnection = DAO()

fun main(args: Array<String>) {
	connection.openConnection("ticketsDb.db")
	runApplication<AppApplication>(*args)
}

/**
 * Класс отключения от БД
 */
@RestController
@RequestMapping("/close")
class Disconnector {
	init {
	    connection.closeConnection()
	}
}