package com.testTask.classes

import com.testTask.interfaces.Statuses

/**
 * Модель статуса тикета
 *
 * @param id идентификатор пользователя
 * @param status название статуса
 */
class Status(private val id: Int, private val status: String): Statuses {
    override fun toString(): String {
        return "Номер статуса: $id, Название статуса: $status"
    }
}