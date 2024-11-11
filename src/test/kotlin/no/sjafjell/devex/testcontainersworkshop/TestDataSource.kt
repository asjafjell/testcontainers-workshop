package no.sjafjell.devex.testcontainersworkshop

import java.sql.Connection
import java.sql.DriverManager
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.testcontainers.containers.MSSQLServerContainer


object TestDataSource {

    fun jdbcTemplate(): JdbcTemplate {
        val container = MSSQLServerContainer("mcr.microsoft.com/mssql/server:2022-latest").acceptLicense()
        container.start()

        val connection = DriverManager.getConnection(
            container.getJdbcUrl(),
            container.getUsername(),
            container.getPassword()
        )

        val dataSource = DriverManagerDataSource(
            container.jdbcUrl,
            container.username,
            container.password
        );

        val jdbcTemplate = JdbcTemplate(dataSource)
        migrate2(jdbcTemplate)

//        migrate(connection)

        return jdbcTemplate
    }

    fun migrate2(jdbcTemplate: JdbcTemplate) {
        jdbcTemplate.execute("CREATE TABLE person (id INT PRIMARY KEY, name NVARCHAR(100), department NVARCHAR(100)) ")
        jdbcTemplate.execute("INSERT INTO person (id, name, department) VALUES (1, 'Aleksander', 'Teknologi')")
    }

    fun migrate(connection: Connection) {
        println("Migrating database ...")
        connection
            .use { connection ->
                connection
                    .createStatement()
                    .use { statement ->
                        statement.execute("CREATE TABLE person (id INT PRIMARY KEY, name NVARCHAR(100), department NVARCHAR(100))")
                    }
            }
        println("Database migrated with a new table!")
    }
}
