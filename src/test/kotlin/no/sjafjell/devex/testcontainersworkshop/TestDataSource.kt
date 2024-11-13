package no.sjafjell.devex.testcontainersworkshop

///*
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.testcontainers.containers.MSSQLServerContainer

object TestDataSource {

    fun jdbcTemplate(): JdbcTemplate {
        val container: MSSQLServerContainer<*> = createTestcontainerForDatabase()

        val dataSource = createDataSource(container)
        val jdbcTemplate = JdbcTemplate(dataSource)

        migrate(jdbcTemplate)

        return jdbcTemplate
    }

    fun createTestcontainerForDatabase(): MSSQLServerContainer<*> {
        //TODO("create container of type MSSQLServerContainer here. Specify the docker image for mssql server")

        //DELETE THESE LINES
        val container: MSSQLServerContainer<*> = MSSQLServerContainer("mcr.microsoft.com/mssql/server:2022-latest").acceptLicense()
        container.start()

        return container
    }

    fun createDataSource(container : MSSQLServerContainer<*>): DriverManagerDataSource {
        //TODO("create a DriverManagerDataSource with connection details from the container")
        //DELETE THESE LINES
        val dataSource = DriverManagerDataSource(
            container.jdbcUrl,
            container.username,
            container.password
        )

        return dataSource
    }

    private fun migrate(jdbcTemplate: JdbcTemplate) {
        println("Migrating database ...")
        jdbcTemplate.execute("CREATE TABLE person (id INT PRIMARY KEY, name NVARCHAR(100), department NVARCHAR(100)) ")
        jdbcTemplate.execute("INSERT INTO person (id, name, department) VALUES (1, 'Aleksander', 'Teknologi')")
        println("Database migrated with a new table!")
    }
}
//*/
