package com.testTask.services

import com.testTask.classes.Ticket
import com.testTask.interfaces.DBConnection
import com.testTask.interfaces.Tickets
import com.testTask.types.QueryTypes
import com.testTask.util.QueryBuilder

/**
 * Сервис для работы с тикетами
 *
 * @param dao подключение к БД
 */
class TicketService(private val dao: DBConnection) {

    private fun findTickets(): List<Tickets>{
        val result: MutableList<Tickets> = mutableListOf()
        var query = QueryBuilder.Builder(QueryTypes.SELECT, "tickets")
                .withSelect()
        val tickets = dao.queryExecute(query.build().toString(), QueryTypes.SELECT) ?:
            return listOf()
        while (tickets.next()){
            val id = tickets.getInt("id")
            val description = tickets.getString("description")
            val workerId = tickets.getInt("worker_id")
            val statusId = tickets.getInt("status_id")
            query = QueryBuilder.Builder(QueryTypes.SELECT, "users")
                    .withSelect(listOf("name"))
                    .withWhere(listOf("id"), listOf(workerId.toString()))
            val worker = dao.queryExecute(query.build().toString(), "name", QueryTypes.SELECT)
            query = QueryBuilder.Builder(QueryTypes.SELECT, "statuses")
                    .withSelect(listOf("status"))
                    .withWhere(listOf("id"), listOf(statusId.toString()))
            val status = dao.queryExecute(query.build().toString(), "status", QueryTypes.SELECT)
            val workerName = if (worker?.size == 0) "0" else worker?.get(0) ?: "0"
            val statusName = if (status?.size == 0) "0" else status?.get(0) ?: "0"
            result.add(Ticket(id, workerName, statusName, description))
        }
        return result
    }

    /**
     * Метод создания тикета
     *
     * @param description описание задачи
     */
    fun createTicket(description: String){
        val query = QueryBuilder.Builder(QueryTypes.INSERT, "tickets")
                .withInsert("description")
                .withValues(listOf(description))
                .build().toString()

        dao.queryExecute(query, QueryTypes.INSERT)
    }

    /**
     * Метод удаления тикета
     *
     * @param id идентификатор тикета
     */
    fun deleteTicket(id: Int){
        val query = QueryBuilder.Builder(QueryTypes.DELETE, "tickets")
                .withInsert("id")
                .withConditions("$id")
                .build().toString()

        dao.queryExecute(query, QueryTypes.DELETE)
    }

    /**
     * Метод назначения работника на исполнение задачи
     *
     * @param ticketId идентификатор тикета
     * @param workerId идентификатор пользователя
     */
    fun setWorker(ticketId: Int, workerId: Int){
        val query = QueryBuilder.Builder(QueryTypes.UPDATE, "tickets")
                .withInsert("worker_id")
                .withConditions(workerId.toString())
                .withWhere(listOf("id"), listOf(ticketId.toString()))
                .build().toString()

        dao.queryExecute(query, QueryTypes.UPDATE)
    }

    /**
     * Метод установки статуса тикета
     *
     * @param ticketId идентификтор тикета
     * @param statusId идентификатор статуса
     */
    fun setStatus(ticketId: Int, statusId: Int){
        val query = QueryBuilder.Builder(QueryTypes.UPDATE, "tickets")
                .withInsert("worker_id")
                .withConditions(statusId.toString())
                .withWhere(listOf("id"), listOf(ticketId.toString()))
                .build().toString()

        dao.queryExecute(query, QueryTypes.UPDATE)
    }

    /**
     * Метод обновления описания задачи
     *
     * @param ticketId идентификатор тикета
     * @param description новое описание задачи
     */
    fun setDescription(ticketId: Int, description: String){
        val query = QueryBuilder.Builder(QueryTypes.UPDATE, "tickets")
                .withInsert("worker_id")
                .withConditions(description)
                .withWhere(listOf("id"), listOf(ticketId.toString()))
                .build().toString()

        dao.queryExecute(query, QueryTypes.UPDATE)
    }

    /**
     * Метод вывода всех тикетов
     */
    fun showAll(): List<String>{
        val output: MutableList<String> = mutableListOf()
        val tickets = findTickets()
        if (tickets.isEmpty())
            return listOf("Тикеты не найдены")
        for(ticket in tickets){
            output.add(ticket.toString())
        }
        return output
    }

}