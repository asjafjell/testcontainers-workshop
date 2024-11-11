package no.sjafjell.devex.testcontainersworkshop

import java.sql.ResultSet
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestcontainersWorkshopApplicationTests {

    @Test
    fun contextLoads() {
        println("Loading test")

        TestDataSource
            .databaseConnection()
            .use { connection ->
                connection
                    .createStatement()
                    .use { statement ->
                        statement.execute("CREATE TABLE test_table (id INT PRIMARY KEY, name NVARCHAR(100))")
                        statement.execute("INSERT INTO test_table (id, name) VALUES (1, 'Testdata')")

                        val resultSet: ResultSet = statement.executeQuery("SELECT name FROM test_table WHERE id = 1")
                        if (resultSet.next()) {
                            val name = resultSet.getString("name")
                        }
                    }
            }


        println("Datasource ${TestDataSource.databaseConnection()} created")
    }

}
