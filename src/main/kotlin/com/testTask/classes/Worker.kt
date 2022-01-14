package com.testTask.classes

import com.testTask.interfaces.Workers

/**
 * Модель пользователя
 *
 * @param id идентификатор пользователя
 * @param name имя пользователя
 */
class Worker(private val id: Int, private val name: String) : Workers {
    override fun toString(): String {
        return "Номер пользователя: $id, Имя пользователя: $name"
    }
}