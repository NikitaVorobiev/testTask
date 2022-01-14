package com.testTask.classes

import com.testTask.services.TicketService
import com.testTask.database.DAO
import com.testTask.interfaces.DBConnection
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun testTicketController(){
        val connection: DBConnection = DAO()
        Assertions.assertTrue(connection.openConnection("ticketsDb.db"))

        val result = TicketService(connection)
        assertEquals("Номер тикета:1, исполнитель:Не назначен исполнитель, статуст тикета:Не назначен статус, описание:Work on this task.",
                result.showAll()[0]
        )
        assertEquals("Номер тикета:2, исполнитель:John, статуст тикета:at work, описание:Do your best!",
                result.showAll()[1]
        )

        connection.closeConnection()
    }

    @Test
    fun createAndDeleteTest(){
        val connection: DBConnection = DAO()
        Assertions.assertTrue(connection.openConnection("ticketsDb.db"))
        val result = TicketService(connection)

        result.createTicket("new ticket")
        //result.deleteTicket(5)

        connection.closeConnection()
    }

    @Test
    fun updateTest(){
        val connection: DBConnection = DAO()
        Assertions.assertTrue(connection.openConnection("ticketsDb.db"))
        val result = TicketService(connection)

        result.setWorker(2,2)
        result.setWorker(2,1)

        connection.closeConnection()
    }
}