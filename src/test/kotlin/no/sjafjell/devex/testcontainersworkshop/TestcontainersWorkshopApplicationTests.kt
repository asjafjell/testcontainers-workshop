package no.sjafjell.devex.testcontainersworkshop

import java.sql.ResultSet
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestcontainersWorkshopApplicationTests {

    data class Person(val name: String, val avdeling: String)

    fun mapToPerson(rs: ResultSet, currentIndex: Int): Person = Person(
        name = rs.getString("name"),
        avdeling = rs.getString("department")
    )

    @Test
    fun `can insert and get person`() {
        TestDataSource
            .jdbcTemplate()
            .queryForObject(
                "SELECT name, department FROM person WHERE id = 1",
                { resultSet: ResultSet, currentIndex: Int -> this.mapToPerson(resultSet, currentIndex) }
            )

        println("Datasource ${TestDataSource.jdbcTemplate()} created")
    }

}
