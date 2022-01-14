package com.testTask.database

import com.testTask.interfaces.DBConnection
import com.testTask.types.QueryTypes
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

/**
 * класс взаимодействия с БД
 */
class DAO: DBConnection {

    /**
     * подключение к БД
     */
    private var conn : Connection? = null

    override fun openConnection(dbName : String) : Boolean {
        if (conn == null)
            conn = DriverManager.getConnection("jdbc:sqlite:$dbName")

        return conn != null
    }

    override fun closeConnection() : Boolean {
        if (conn != null) {
            conn!!.close()
            return conn!!.isClosed
        }

        return false
    }

    override fun queryExecute(query: String, column: String, type: QueryTypes): List<String>? {
        var exec: ResultSet? = null
        val statement = conn?.createStatement()
        if (type == QueryTypes.SELECT)
            exec = statement?.executeQuery(query)!!
        else
            statement!!.execute(query)
        val result = mutableListOf<String>()

        return if (type == QueryTypes.SELECT) {
            if (exec != null) {
                while (exec.next()) {
                    result.add(exec.getString(column))
                }
            }
            result
        } else
            null
    }

    override fun queryExecute(query: String, type : QueryTypes): ResultSet? {
        var exec: ResultSet? = null
        val statement = conn?.createStatement()
        if (type == QueryTypes.SELECT)
            exec = statement?.executeQuery(query)!!
        else
            statement!!.execute(query)

        return if (type == QueryTypes.SELECT) {
            exec
        } else
            null
    }
}