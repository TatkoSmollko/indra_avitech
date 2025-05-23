package org.assignment.configuration;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

public class DataSourceConfig {

	public static DataSource createH2DataSource() {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
		ds.setUser("sa");
		ds.setPassword("");
		return ds;
	}
}
