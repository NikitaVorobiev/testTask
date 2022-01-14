package com.testTask.services

import com.testTask.classes.Worker
import com.testTask.interfaces.DBConnection
import com.testTask.interfaces.Workers
import com.testTask.types.QueryTypes
import com.testTask.util.QueryBuilder

/**
 * Сервис для работы с пользователями
 *
 * @param dao подключение к БД
 */
class WorkerService(private val dao: DBConnection) {
    private fun findWorkers(): List<Workers>{
        val result: MutableList<Workers> = mutableListOf()
        var query = QueryBuilder.Builder(QueryTypes.SELECT, "users")
                .withSelect()
        val workers = dao.queryExecute(query.build().toString(), QueryTypes.SELECT) ?:
        return listOf()

        while (workers.next()){
            val id = workers.getInt("id")
            val name = workers.getString("name")
            result.add(Worker(id, name))
        }
        return result
    }

    /**
     * Метод вывода всех пользоваетелй
     */
    fun showAll(): List<String>{
        val output: MutableList<String> = mutableListOf()
        val workers = findWorkers()
        if (workers.isEmpty())
            return listOf("Пользователи не найдены")
        for(worker in workers){
            output.add(worker.toString())
        }
        return output
    }

    /**
     * Метод создания нового пользователя
     *
     * @param name имя пользователя
     */
    fun createUser(name: String){
        val query = QueryBuilder.Builder(QueryTypes.INSERT, "users")
                .withInsert("name")
                .withValues(listOf(name))
                .build().toString()

        dao.queryExecute(query, QueryTypes.INSERT)
    }

    /**
     * Метод удаления пользователя
     *
     * @param id идентификтор пользователя
     */
    fun deleteWorker(id: Int){
        val query = QueryBuilder.Builder(QueryTypes.DELETE, "users")
                .withInsert("id")
                .withConditions("$id")
                .build().toString()

        dao.queryExecute(query, QueryTypes.DELETE)
    }

    /**
     * Метод переименования пользователя
     *
     * @param id идентификатор пользователя
     * @param name новое имя пользователя
     */
    fun renameWorker(id: Int, name: String){
        val query = QueryBuilder.Builder(QueryTypes.UPDATE, "users")
                .withInsert("name")
                .withConditions("'$name'")
                .withWhere(listOf("id"), listOf(id.toString()))
                .build().toString()

        dao.queryExecute(query, QueryTypes.UPDATE)
    }
}