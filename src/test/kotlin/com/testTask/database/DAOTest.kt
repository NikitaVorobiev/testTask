package com.testTask.database

import com.testTask.interfaces.DBConnection
import com.testTask.types.QueryTypes
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DAOTest {
    @Test
    fun testDAO(){
        val result: DBConnection = DAO()

        assertTrue(result.openConnection("ticketsDb.db"))
        assertEquals("[Work on this task., Do your best!]", result.queryExecute(
                "select * from tickets",
                "description",
                QueryTypes.SELECT
        ).toString())
        assertEquals("[John, Alex]", result.queryExecute(
                "select * from users",
                "name",
                QueryTypes.SELECT
        ).toString())
        assertEquals("[at work, close, open]", result.queryExecute(
                "select * from statuses",
                "status",
                QueryTypes.SELECT
        ).toString())

        assertTrue(result.closeConnection())
    }
}