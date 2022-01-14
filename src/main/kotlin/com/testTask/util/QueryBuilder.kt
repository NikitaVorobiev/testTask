package com.testTask.util

import com.testTask.types.QueryTypes

/**
 * Билдер SQL запросов
 *
 * @param table название таблицы
 * @param data данные внутри запроса
 * @param where параметры для запроса SELECT
 * @param values параметры для запроса INSERT
 */
class QueryBuilder(
        private var table: String,
        private var data: String = "",
        private var where: String? = null,
        private var values: String? = null
) {

    private constructor(builder: Builder) : this(
            builder.table, builder.data, builder.where, builder.values
    )

    /**
     * Класс для приватного конструктора, реализует паттерн билдер
     *
     * @property type тип запроса
     * @property table название таблицы
     */
    class Builder(private val type: QueryTypes, val table: String) {

        var data: String = type.toString()
            private set

        var where: String? = null
            private set

        var values: String? = null
            private set

        /**
         * Функция добавления названий выбираемых столбцов, если их несколько
         *
         * @property columns названия столбцов
         */
        fun withSelect(columns: List<String>? = null) = apply {
            if (columns == null){
                data = "*"
            }else {
                data = "("
                columns.forEach {
                    data += "$it, "
                }
                data += ")"
                data = data.replace(", )", ")")
            }
            data = type.toString().replace("[params]", data)
        }

        /**
         * метод замены параметра в запросе
         *
         * @param conditions значение параметра
         */
        fun withConditions(conditions: String) = apply{
            data = data.replace("[conditions]", conditions)
        }

        /**
         * Функция добавления названия столбца в INSERT
         *
         * @property column название столбца
         */
        fun withInsert(column: String) = apply {
            data = data.replace("[params]", column)
        }

        /**
         * Функция добавления параметров WHERE в запрос SELECT
         *
         * @param columns названия столбцов
         * @param value передаваемые значения
         */
        fun withWhere(columns: List<String>, value: List<String>) = apply {
            where = "where"

            for (i in columns.indices)
                where += " ${columns[i]} = '${value[i]}' AND"

            where += ";"
            where = where!!.replace(" AND;", "")
        }

        /**
         * Функция добавления параметров VALUES для INSERT
         */
        fun withValues(value: List<String>) = apply {
            values = "values ("

            for (i in value.indices)
                values += "'${value[i]}', "

            values += ";"
            values = values!!.replace(", ;", ")")
        }

        fun build() = QueryBuilder(this)
    }

    /**
     * Переписанная функция toString возвращает готовый запрос
     */
    override fun toString(): String {
        return if (values == null)
            "$data ${where ?: ""}".replace("[table]", table)
        else
            "$data ${values ?: ""}".replace("[table]", table)
    }
}