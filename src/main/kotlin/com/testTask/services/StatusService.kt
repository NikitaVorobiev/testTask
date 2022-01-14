package com.testTask.services

import com.testTask.classes.Status
import com.testTask.interfaces.DBConnection
import com.testTask.interfaces.Statuses
import com.testTask.types.QueryTypes
import com.testTask.util.QueryBuilder

/**
 * Сервис для работы со статусами
 *
 * @param dao подключение к БД
 */
class StatusService(private val dao: DBConnection) {
    private fun findStatuses(): List<Statuses>{
        val result: MutableList<Statuses> = mutableListOf()
        var query = QueryBuilder.Builder(QueryTypes.SELECT, "statuses")
                .withSelect()
        val statuses = dao.queryExecute(query.build().toString(), QueryTypes.SELECT) ?:
        return listOf()

        while (statuses.next()){
            val id = statuses.getInt("id")
            val status = statuses.getString("status")
            result.add(Status(id, status))
        }
        return result
    }

    /**
     * Метод вывода списка статусов
     */
    fun showAll(): List<String>{
        val output: MutableList<String> = mutableListOf()
        val statuses = findStatuses()
        if (statuses.isEmpty())
            return listOf("Пользователи не найдены")
        for(status in statuses){
            output.add(status.toString())
        }
        return output
    }
}