package com.testTask.interfaces

import com.testTask.types.QueryTypes
import java.sql.ResultSet

interface DBConnection{

    /**
     * открытие подключения к БД
     *
     * @param dbName название БД
     */
    fun openConnection(dbName : String) : Boolean

    /**
     * закрытие подключения к БД
     */
    fun closeConnection() : Boolean

    /**
     * выполнение запроса к БД
     *
     * @param query текст запроса
     * @param type тип запроса
     * @param column возвращаемый столбец
     */
    fun queryExecute(query : String, column: String, type : QueryTypes) : List<String>?

    /**
     * выполнение запроса к БД
     *
     * @param query текст запроса
     * @param type тип запроса
     */
    fun queryExecute(query : String, type : QueryTypes) : ResultSet?
}