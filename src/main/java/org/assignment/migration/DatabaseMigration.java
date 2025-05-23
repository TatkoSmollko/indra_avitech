package org.assignment.migration;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

public class DatabaseMigration {

	public static void migrate(DataSource dataSource) {
		Flyway flyway = Flyway.configure()
							  .dataSource(dataSource)
							  .load();
		flyway.migrate();
	}
}