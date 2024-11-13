package no.sjafjell.devex.testcontainersworkshop

import java.sql.ResultSet
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DatabaseTest {

    data class Person(val name: String, val avdeling: String)

    @Test
    fun `insert and get person`() {
        TestDataSource
            .jdbcTemplate()
            .queryForObject(
                "SELECT name, department FROM person WHERE id = 1",
                { resultSet: ResultSet, currentIndex: Int -> this.mapToPerson(resultSet, currentIndex) }
            )

        println("Datasource ${TestDataSource.jdbcTemplate()} created")
    }

    fun mapToPerson(rs: ResultSet, currentIndex: Int): Person = Person(
        name = rs.getString("name"),
        avdeling = rs.getString("department")
    )

}
