package com.testTask.classes

import com.testTask.services.StatusService
import com.testTask.database.DAO
import com.testTask.interfaces.DBConnection
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StatusTest {
    @Test
    fun testTicketController(){
        val connection: DBConnection = DAO()
        Assertions.assertTrue(connection.openConnection("ticketsDb.db"))

        val result = StatusService(connection)
        Assertions.assertEquals("Номер статуса: 1, Название статуса: at work",
                result.showAll()[0]
        )
        Assertions.assertEquals("Номер статуса: 2, Название статуса: close",
                result.showAll()[1]
        )
        Assertions.assertEquals("Номер статуса: 3, Название статуса: open",
                result.showAll()[2]
        )

        connection.closeConnection()
    }
}