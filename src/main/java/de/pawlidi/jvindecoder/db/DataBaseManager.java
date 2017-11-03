/*
 * Copyright (C) 2016 Maximilian Pawlidi
 *
 */
package de.pawlidi.jvindecoder.db;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import de.pawlidi.jvindecoder.model.VehicleDescriptorSection;
import de.pawlidi.jvindecoder.model.VehicleIndicatorSection;
import de.pawlidi.jvindecoder.model.WMICountry;
import de.pawlidi.jvindecoder.model.WMIManufacturer;
import de.pawlidi.jvindecoder.model.WMIRegion;
import de.pawlidi.utils.log.ILog;
import de.pawlidi.utils.log.Logging;

//https://github.com/h2database/h2database/blob/master/h2/src/test/org/h2/samples/ReadOnlyDatabaseInZip.java
public final class DataBaseManager {

	private volatile static DataBaseManager instance;

	private static final ILog log = Logging.getLog(DataBaseManager.class);

	private ConnectionSource connection;

	/**
	 * Invisible default constructor to construct the database manager.
	 */
	private DataBaseManager() {
		super();
		setup();
	}

	/**
	 * Return singleton database manager instance.
	 * 
	 * @return instance
	 */
	public static DataBaseManager instance() {
		if (instance == null) {
			synchronized (DataBaseManager.class) {
				if (instance == null) {
					instance = new DataBaseManager();
				}
			}
		}
		return instance;
	}

	private void setup() {

		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			log.error("Database driver not exist....");
			throw new RuntimeException("Could not start application without database driver!");
		}

		try {
			connection = new JdbcConnectionSource(
					"jdbc:h2:split:zip:" + getClass().getClassLoader().getResource("META-INF/decoder.mv.db"));
		} catch (SQLException e) {
			log.error("Could not open database connection {0}", e, e.getMessage());
			throw new RuntimeException("Could not start application without database!");
		}

		// https://stackoverflow.com/questions/7254967/ormlite-with-persistent-h2-db-new-tables-not-get-persisted
		// http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_1.html#Getting-Started

	}

	void createEmptyDatabase(final String path) throws SQLException {
		connection = new JdbcConnectionSource("jdbc:h2:file:" + path + ";USER=decoder");
		TableUtils.createTable(connection, WMICountry.class);
		TableUtils.createTable(connection, WMIManufacturer.class);
		TableUtils.createTable(connection, WMIRegion.class);
		TableUtils.createTable(connection, VehicleDescriptorSection.class);
		TableUtils.createTable(connection, VehicleIndicatorSection.class);
	}
}
