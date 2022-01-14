package com.testTask.classes

import com.testTask.interfaces.Tickets

/**
 * Модель тикета
 *
 * @param id идентификатор пользователя
 * @param worker пользователь, на которого назначен тикет
 * @param status статус задачи
 * @param description описание задачи
 */
class Ticket(
        private val id: Int, private var worker: String, private var status: String, private val description: String
        ): Tickets {
    init {
        if (worker == "0")
            worker = "Не назначен исполнитель"
        if (status == "0")
            status = "Не назначен статус"
    }
    override fun toString(): String {
        return "Номер тикета:$id, исполнитель:$worker, статуст тикета:$status, описание:$description"
    }
}