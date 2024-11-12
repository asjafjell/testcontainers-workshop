package no.sjafjell.devex.testcontainersworkshop

import java.sql.Connection
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.testcontainers.containers.MSSQLServerContainer


object TestDataSource {

    fun jdbcTemplate(): JdbcTemplate {
        // create container of type MSSQLServerContainer here. Specify the docker image for mssql server
        // Todo: set container to null here and remove start
        val container = MSSQLServerContainer("mcr.microsoft.com/mssql/server:2022-latest").acceptLicense()
        container.start()

        val dataSource = DriverManagerDataSource(
            container.jdbcUrl,
            container.username,
            container.password
        )

        val jdbcTemplate = JdbcTemplate(dataSource)
        migrate(jdbcTemplate)

        return jdbcTemplate
    }

    fun migrate(jdbcTemplate: JdbcTemplate) {
        println("Migrating database ...")
        jdbcTemplate.execute("CREATE TABLE person (id INT PRIMARY KEY, name NVARCHAR(100), department NVARCHAR(100)) ")
        jdbcTemplate.execute("INSERT INTO person (id, name, department) VALUES (1, 'Aleksander', 'Teknologi')")
        println("Database migrated with a new table!")
    }
}
