package com.testTask.classes

import com.testTask.services.WorkerService
import com.testTask.database.DAO
import com.testTask.interfaces.DBConnection
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WorkerTest {
    @Test
    fun testTicketController(){
        val connection: DBConnection = DAO()
        Assertions.assertTrue(connection.openConnection("ticketsDb.db"))

        val result = WorkerService(connection)
        Assertions.assertEquals("Номер пользователя: 1, Имя пользователя: John",
                result.showAll()[0]
        )
        Assertions.assertEquals("Номер пользователя: 2, Имя пользователя: Alex",
                result.showAll()[1]
        )

        connection.closeConnection()
    }

    @Test
    fun createAndDeleteTest(){
        val connection: DBConnection = DAO()
        Assertions.assertTrue(connection.openConnection("ticketsDb.db"))
        val result = WorkerService(connection)

        result.createUser("Newbie")
        //result.deleteWorker(3)
        connection.closeConnection()
    }

    @Test
    fun updateTest(){
        val connection: DBConnection = DAO()
        Assertions.assertTrue(connection.openConnection("ticketsDb.db"))
        val result = WorkerService(connection)

        result.renameWorker(2,"Newbie")
        result.renameWorker(2,"Alex")

        connection.closeConnection()
    }
}