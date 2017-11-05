/*
 * Copyright (C) 2016 Maximilian Pawlidi
 *
 */
package de.pawlidi.jvindecoder.db;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
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
	private WMIRegionManager regionManager;
	private WMICountryManager countryManager;
	private WMIManufacturerManager manufacturerManager;

	private Dao<VehicleDescriptorSection, String> vdsDao;
	private QueryBuilder<VehicleDescriptorSection, String> vdsQueryBuilder;

	private Dao<VehicleIndicatorSection, String> visDao;
	private QueryBuilder<VehicleIndicatorSection, String> visQueryBuilder;

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

		setupDBAccess();

		// https://stackoverflow.com/questions/7254967/ormlite-with-persistent-h2-db-new-tables-not-get-persisted
		// http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_1.html#Getting-Started

	}

	private void setupDBAccess() {
		try {
			countryManager = new WMICountryManager(connection);
			manufacturerManager = new WMIManufacturerManager(connection);
			regionManager = new WMIRegionManager(connection);

			vdsDao = DaoManager.createDao(connection, VehicleDescriptorSection.class);
			vdsQueryBuilder = vdsDao.queryBuilder();
			visDao = DaoManager.createDao(connection, VehicleIndicatorSection.class);
			visQueryBuilder = visDao.queryBuilder();
		} catch (SQLException e) {
			log.error("Could not initialize daos {0}", e, e.getMessage());
			throw new RuntimeException("Could not start application without database configuration!");
		}
	}

	void createEmptyDatabase(final String path) throws SQLException, IOException {
		connection = new JdbcConnectionSource("jdbc:h2:file:" + path + ";USER=decoder");

		TableUtils.createTable(connection, WMICountry.class);
		TableUtils.createTable(connection, WMIManufacturer.class);
		TableUtils.createTable(connection, VehicleDescriptorSection.class);
		TableUtils.createTable(connection, VehicleIndicatorSection.class);
		connection.close();
	}

	void insertWMIRegion(final String path) throws SQLException, IOException {
		connection = new JdbcConnectionSource("jdbc:h2:file:" + path + ";USER=decoder");
		setupDBAccess();
		regionManager.create(new WMIRegion("Africa", "[A-H]"));
		regionManager.create(new WMIRegion("Asia", "[J-R]"));
		regionManager.create(new WMIRegion("Europe", "[S-Z]"));
		regionManager.create(new WMIRegion("North America", "[1-5]"));
		regionManager.create(new WMIRegion("Oceania", "[6-7]"));
		regionManager.create(new WMIRegion("South America", "[890]"));
		connection.close();
	}

	void insertWMICountry(final String path) throws SQLException, IOException {
		connection = new JdbcConnectionSource("jdbc:h2:file:" + path + ";USER=decoder");
		setupDBAccess();
		countryManager.create(new WMICountry("SouthAfrica", "[A]", "[A-H]"));
		countryManager.create(new WMICountry("South Africa", "[A]", "[A-H]"));
		countryManager.create(new WMICountry("Ivory Coast", "[A]", "[J-N]"));
		countryManager.create(new WMICountry("Africa", "[A]", "[P-Z0-9]"));
		countryManager.create(new WMICountry("Africa", "[B-D]", "[S-Z0-9]"));
		countryManager.create(new WMICountry("Africa", "[E-F]", "[L-Z0-9]"));
		countryManager.create(new WMICountry("Africa", "[G-H]", "[A-Z0-9]"));
		countryManager.create(new WMICountry("Angola", "[B]", "[A-E]"));
		countryManager.create(new WMICountry("Kenya", "[B]", "[F-K]"));
		countryManager.create(new WMICountry("Tanzania", "[B]", "[L-R]"));
		countryManager.create(new WMICountry("Benin", "[C]", "[A-E]"));
		countryManager.create(new WMICountry("Madagascar", "[C]", "[F-K]"));
		countryManager.create(new WMICountry("Tunisia", "[C]", "[L-R]"));
		countryManager.create(new WMICountry("Egypt", "[D]", "[A-E]"));
		countryManager.create(new WMICountry("Morocco", "[D]", "[F-H]"));
		countryManager.create(new WMICountry("Zambia", "[D]", "[L-R]"));
		countryManager.create(new WMICountry("Ghana", "[E]", "[A-E]"));
		countryManager.create(new WMICountry("Mozambique", "[E]", "[F-H]"));
		countryManager.create(new WMICountry("India", "[F]", "[A-E]"));
		countryManager.create(new WMICountry("India", "[M]", "[A-E]"));
		countryManager.create(new WMICountry("Nigeria", "[F]", "[F-K]"));
		countryManager.create(new WMICountry("Japan", "[A]", "[A-Z0-9]"));
		countryManager.create(new WMICountry("Sri Lanka", "[A]", "[A-E]"));
		countryManager.create(new WMICountry("Israel", "[K]", "[F-K]"));
		countryManager.create(new WMICountry("South Korea", "[K]", "[L-R]"));
		countryManager.create(new WMICountry("Kazakhstan", "[K]", "[S-Z0-9]"));
		countryManager.create(new WMICountry("China", "[L]", "[A-Z0-9]"));
		countryManager.create(new WMICountry("Indonesia", "[M]", "[F-K]"));
		countryManager.create(new WMICountry("Thailand", "[M]", "[L-R]"));
		countryManager.create(new WMICountry("Iran", "[N]", "[A-E]"));
		countryManager.create(new WMICountry("Pakistan", "[N]", "[F-K]"));
		countryManager.create(new WMICountry("Turkey", "[N]", "[L-R]"));
		countryManager.create(new WMICountry("Pakistan", "[N]", "[F-K]"));
		countryManager.create(new WMICountry("Asia", "[M-P]", "[S-Z0-9]"));
		countryManager.create(new WMICountry("Philippines", "[P]", "[A-E]"));
		countryManager.create(new WMICountry("Singapore", "[P]", "[F-K]"));
		countryManager.create(new WMICountry("Malaysia", "[N]", "[L-R]"));
		countryManager.create(new WMICountry("UAE", "[P]", "[A-E]"));
		countryManager.create(new WMICountry("Taiwan", "[R]", "[F-K]"));
		countryManager.create(new WMICountry("Vietnam", "[R]", "[L-R]"));
		countryManager.create(new WMICountry("Saudi Arabia", "[R]", "[S-Z0-9]"));
		countryManager.create(new WMICountry("United Kingdom", "[S]", "[A-M]"));
		countryManager.create(new WMICountry("East Germany", "[S]", "[N-T]"));
		countryManager.create(new WMICountry("Poland", "[S]", "[U-Z]"));
		countryManager.create(new WMICountry("Latvia", "[S]", "[1-4]"));
		countryManager.create(new WMICountry("Europe", "[S]", "[5-90]"));
		countryManager.create(new WMICountry("Europe", "[T]", "[2-90]"));
		countryManager.create(new WMICountry("Europe", "[U]", "[A-G]"));
		countryManager.create(new WMICountry("Europe", "[U]", "[1-4]"));
		countryManager.create(new WMICountry("Europe", "[U]", "[8-90]"));
		countryManager.create(new WMICountry("Europe", "[Z]", "[S-W]"));
		countryManager.create(new WMICountry("Europe", "[Z]", "[6-90]"));
		countryManager.create(new WMICountry("Switzerland", "[T]", "[A-H]"));
		countryManager.create(new WMICountry("Czech Republic", "[T]", "[J-P]"));
		countryManager.create(new WMICountry("Hungary", "[T]", "[R-V]"));
		countryManager.create(new WMICountry("Portugal", "[T]", "[W-Z1]"));
		countryManager.create(new WMICountry("Denmark", "[U]", "[H-M]"));
		countryManager.create(new WMICountry("Ireland", "[U]", "[N-T]"));
		countryManager.create(new WMICountry("Romania", "[U]", "[U-Z]"));
		countryManager.create(new WMICountry("Slovakia", "[U]", "[5-7]"));
		countryManager.create(new WMICountry("Austria", "[V]", "[A-E]"));
		countryManager.create(new WMICountry("France", "[V]", "[F-R]"));
		countryManager.create(new WMICountry("Spain", "[V]", "[S-W]"));
		countryManager.create(new WMICountry("Serbia", "[V]", "[X-Z1-2]"));
		countryManager.create(new WMICountry("Croatia", "[V]", "[3-5]"));
		countryManager.create(new WMICountry("Estonia", "[V]", "[6-90]"));
		countryManager.create(new WMICountry("Germany", "[W]", "[A-Z0-9]"));
		countryManager.create(new WMICountry("Bulgaria", "[X]", "[A-E]"));
		countryManager.create(new WMICountry("Greece", "[X]", "[F-K]"));
		countryManager.create(new WMICountry("Netherlands", "[X]", "[L-R]"));
		countryManager.create(new WMICountry("USSR", "[X]", "[S-W]"));
		countryManager.create(new WMICountry("Luxembourg", "[X]", "[X-Z1-2]"));
		countryManager.create(new WMICountry("Russia", "[X]", "[3-90]"));
		countryManager.create(new WMICountry("Belgium", "[Y]", "[A-E]"));
		countryManager.create(new WMICountry("Finland", "[Y]", "[F-K]"));
		countryManager.create(new WMICountry("Malta", "[Y]", "[L-R]"));
		countryManager.create(new WMICountry("Sweden", "[Y]", "[S-W]"));
		countryManager.create(new WMICountry("Norway", "[Y]", "[X-Z1-2]"));
		countryManager.create(new WMICountry("Belarus", "[Y]", "[3-5]"));
		countryManager.create(new WMICountry("Ukraine", "[Y]", "[6-90]"));
		countryManager.create(new WMICountry("Italy", "[Y]", "[A-R]"));
		countryManager.create(new WMICountry("Slovenia", "[Z]", "[X-Z1-2]"));
		countryManager.create(new WMICountry("Lithuania", "[Z]", "[3-5]"));
		countryManager.create(new WMICountry("United States", "[1]", "[A-Z0-9]"));
		countryManager.create(new WMICountry("United States", "[4]", "[A-Z0-9]"));
		countryManager.create(new WMICountry("United States", "[5]", "[A-Z0-9]"));
		countryManager.create(new WMICountry("Canada", "[2]", "[A-Z0-9]"));
		countryManager.create(new WMICountry("Mexico", "[3]", "[A-Z0-9]"));
		countryManager.create(new WMICountry("Australia", "[6]", "[A-W]"));
		countryManager.create(new WMICountry("Oceania", "[6]", "[X-Z0-9]"));
		countryManager.create(new WMICountry("Oceania", "[7]", "[F-Z0-9]"));
		countryManager.create(new WMICountry("New Zealand", "[7]", "[A-E]"));
		countryManager.create(new WMICountry("Argentina", "[8]", "[A-E]"));
		countryManager.create(new WMICountry("Chile", "[8]", "[F-K]"));
		countryManager.create(new WMICountry("Ecuador", "[8]", "[L-R]"));
		countryManager.create(new WMICountry("Peru", "[8]", "[S-W]"));
		countryManager.create(new WMICountry("Venezuela", "[8]", "[X-Z1-2]"));
		countryManager.create(new WMICountry("South America", "[8]", "[2-90]"));
		countryManager.create(new WMICountry("South America", "[9]", "[0]"));
		countryManager.create(new WMICountry("South America", "[0]", "[A-Z0-9]"));
		countryManager.create(new WMICountry("Brazil", "[9]", "[A-E]"));
		countryManager.create(new WMICountry("Brazil", "[9]", "[3-9]"));
		countryManager.create(new WMICountry("Colombia", "[9]", "[F-K]"));
		countryManager.create(new WMICountry("Paraguay", "[9]", "[L-R]"));
		countryManager.create(new WMICountry("Uruguay", "[9]", "[S-W]"));
		countryManager.create(new WMICountry("Trinidad Tobago", "[9]", "[X-Z1-2]"));
		connection.close();
	}

	/**
	 * @return the regionManager
	 */
	public WMIRegionManager getRegionManager() {
		return regionManager;
	}

	/**
	 * @return the countryManager
	 */
	public WMICountryManager getCountryManager() {
		return countryManager;
	}

	/**
	 * @return the manufacturerManager
	 */
	public WMIManufacturerManager getManufacturerManager() {
		return manufacturerManager;
	}

}
