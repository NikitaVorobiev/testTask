package com.testTask.app

import com.testTask.services.StatusService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Контроллер статусов тикетов
 */
@RestController
@RequestMapping("/statuses")
class StatusController {
    /**
     * Сервис статусов
     */
    val service = StatusService(connection)

    /**
     * Метод вывода свех статусов
     */
    @RequestMapping("/show")
    fun showStatuses(): List<String>{
        return service.showAll()
    }
}