package com.testTask.app

import com.testTask.services.WorkerService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Контроллер для работы с пользователями
 */
@RestController
@RequestMapping("/workers")
class WorkerController {
    /**
     * Идентификатор выбранного для изменений пользователя
     */
    var activeWorker: Int? = null

    /**
     * Сервис пользователей
     */
    val service = WorkerService(connection)

    /**
     * Метод вывода всех пользователей
     */
    @RequestMapping("/show")
    fun showWorkers(): List<String>{
        return service.showAll()
    }

    /**
     * Метод выбора активного пользователя
     *
     * @param id идентификатор пользователя
     */
    @RequestMapping("/select/{id}")
    fun chooseTicket(@PathVariable id: Int): String{
        activeWorker = id
        return "Выбран пользователь номер $id"
    }

    /**
     * Метод удаления пользователя
     *
     * @param id идентификтор пользователя
     */
    @RequestMapping("/delete/{id}")
    fun deleteWorker(@PathVariable id: Int): String{
        service.deleteWorker(id)
        return "Удаление успешно"
    }

    /**
     * Метод создания нового пользователя
     *
     * @param name имя нового польщзователя
     */
    @RequestMapping("/create/{name}")
    fun createWorker(@PathVariable name: String): String{
        service.createUser(name)
        return "Создание успешно"
    }

    /**
     * Метод переименования пользователя
     *
     * @param name новое имя пользователя
     */
    @RequestMapping("/rename/{name}")
    fun renameWorker(@PathVariable name: String): String{
        return if (activeWorker == null) {
            "Активный пользователь не выбран"
        } else {
            service.renameWorker(activeWorker!!, name)
            "Удаление успешно"
        }
    }
}