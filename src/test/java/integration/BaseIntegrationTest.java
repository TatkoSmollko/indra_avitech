package integration;


import org.assignment.configuration.DataSourceConfig;
import org.assignment.migration.DatabaseMigration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseIntegrationTest {

	protected DataSource dataSource;

	@BeforeAll
	void initDatabase() {
		dataSource = DataSourceConfig.createH2DataSource();
		DatabaseMigration.migrate(dataSource);
	}

	@BeforeEach
	void cleanDatabase() throws SQLException {
		try (Connection conn = dataSource.getConnection();
				Statement stmt = conn.createStatement()) {
			stmt.execute("DELETE FROM SUSERS");
		}
	}
}
