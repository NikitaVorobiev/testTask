package com.testTask.types

/**
 * Класс для хранения пресетов запросов
 */
enum class QueryTypes {
    /**
     * Select-запрос
     */
    SELECT {
        private val type = "select [params] from [table]"

        override fun toString(): String {
            return type
        }
    },

    /**
     * Insert-запрос
     */
    INSERT {
        private val type = "insert into [table] ([params])"

        override fun toString(): String {
            return type
        }
    },

    /**
     * Delete-запрос
     */
    DELETE {
        private val type = "delete from [table] where ([params] = [conditions])"

        override fun toString(): String {
            return type
        }
    },

    /**
     * Delete-запрос
     */
    UPDATE {
        private val type = "update [table] set [params] = [conditions]"

        override fun toString(): String {
            return type
        }
    }
}