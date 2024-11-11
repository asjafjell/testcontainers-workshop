package no.sjafjell.devex.testcontainersworkshop

import java.sql.Connection
import java.sql.DriverManager
import org.testcontainers.containers.MSSQLServerContainer

object TestDataSource {

    fun databaseConnection(): Connection {
        val container = MSSQLServerContainer("mcr.microsoft.com/mssql/server:2022-latest").acceptLicense()
        container.start()

        return DriverManager.getConnection(
            container.getJdbcUrl(),
            container.getUsername(),
            container.getPassword()
        )
    }
}
