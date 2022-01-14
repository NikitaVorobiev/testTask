package com.testTask.app

import com.testTask.services.TicketService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Контроллер для работы с тикетами
 */
@RestController
@RequestMapping("/tickets")
class TicketController {
    /**
     * Идентификатор активного тикета
     */
    var activeTicketId: Int? = null

    /**
     * Сервис тикетов
     */
    val service = TicketService(connection)

    /**
     * Метод вывода тикетов
     */
    @RequestMapping("/show")
    fun showTickets(): List<String>{
        return service.showAll()
    }

    /**
     * Метод выбора активного тикета для изменений
     *
     * @param id идентификатор тикета
     */
    @RequestMapping("/select/{id}")
    fun chooseTicket(@PathVariable id: Int): String{
        activeTicketId = id
        return "Выбран тикент номер $id"
    }

    /**
     * Метод удаления тикета
     *
     * @param id идентификатор тикета
     */
    @RequestMapping("/delete/{id}")
    fun deleteTicket(@PathVariable id: Int): String{
        service.deleteTicket(id)
        return "Удаление успешно"
    }

    /**
     * Метод назначения исполнителя для тикета
     *
     * @param id идентификатор тикета
     */
    @RequestMapping("/setWorker/{id}")
    fun setWorker(@PathVariable id: Int): String {
        return if (activeTicketId == null)
            "Активный тикет не выбран"
        else {
            service.setWorker(activeTicketId!!, id)
            "Исполнитель установлен"
        }
    }

    /**
     *  Метод смены статуса тикета
     *
     *  @param id идентификатор статуса
     */
    @RequestMapping("/setStatus/{id}")
    fun setStatus(@PathVariable id: Int): String{
        return if (activeTicketId == null)
            "Активный тикет не выбран"
        else {
            service.setStatus(activeTicketId!!, id)
            "Статус задачи изменен"
        }
    }

    /**
     * Метод установки описания задачи в тикете
     *
     * @param description описание
     */
    @RequestMapping("/setDescription/{description}")
    fun setDescription(@PathVariable description: String): String{
        return if (activeTicketId == null)
            "Активный тикет не выбран"
        else {
            service.setDescription(activeTicketId!!, description)
            "Статус задачи изменен"
        }
    }
}
