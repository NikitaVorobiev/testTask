package com.testTask.util

import com.testTask.types.QueryTypes
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class QueryBuilderTest {
    @Test
    fun testBuilder() {
        var builder = QueryBuilder.Builder(QueryTypes.SELECT, "table")
                .withSelect(listOf("column1", "column2"))
                .withWhere(listOf("column1", "column2"), listOf("value1", "value2"))

        assertEquals(
                "select (column1, column2) from table where column1 = 'value1' AND column2 = 'value2'",
                builder.build().toString()
        )

        builder = QueryBuilder.Builder(QueryTypes.INSERT, "table")
                .withInsert("column1")
                .withValues(listOf("value1", "value2"))

        assertEquals(
                "insert into table (column1) values ('value1', 'value2')",
                builder.build().toString()
        )

        builder = QueryBuilder.Builder(QueryTypes.DELETE, "table")
                .withConditions("conditions")
                .withInsert("params")

        assertEquals(
                "delete from table where (params = conditions) ",
                builder.build().toString()
        )

        builder = QueryBuilder.Builder(QueryTypes.UPDATE, "table")
                .withConditions("conditions")
                .withInsert("params")

        assertEquals(
                "update table set params = conditions ",
                builder.build().toString()
        )
    }
}