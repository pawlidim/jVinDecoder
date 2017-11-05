/*
 * Copyright (C) 2017 Maximilian Pawlidi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.pawlidi.jvindecoder.db;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import de.pawlidi.jvindecoder.model.WMICountry;
import de.pawlidi.utils.log.ILog;
import de.pawlidi.utils.log.Logging;

/**
 * 
 * @author PAWLIDIM
 *
 */
public final class WMICountryManager {

	private static final ILog log = Logging.getLog(WMICountryManager.class);

	private final Dao<WMICountry, String> countryDao;
	private final QueryBuilder<WMICountry, String> countryQueryBuilder;

	WMICountryManager(ConnectionSource connection) throws SQLException {
		super();
		try {
			TableUtils.createTable(connection, WMICountry.class);
		} catch (SQLException e) {
			log.error("Could not create table {0}", e.getMessage());
		}
		countryDao = DaoManager.createDao(connection, WMICountry.class);
		countryQueryBuilder = countryDao.queryBuilder();
	}

	/**
	 * Creates new wmi country.
	 * 
	 * @param region
	 * @return true, if creation was successful, false otherwise
	 */
	public boolean create(WMICountry country) {
		if (country == null || country.getName() == null || country.getPrefixRegex() == null
				|| country.getSuffixRegex() == null || country.getUuid() == null) {
			return false;
		}
		try {
			countryDao.create(country);
			log.info("Create new wmi country with name {0} and regex {1},{2}", country.getName(),
					country.getPrefixRegex(), country.getSuffixRegex());
			return true;
		} catch (SQLException e) {
			log.error("Could not create wmi country - {0}", e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String id) {
		if (id == null || id.trim().isEmpty()) {
			return false;
		}
		try {
			countryDao.deleteById(id);
			log.info("Delete new wmi country with id {0} ", id);
			return true;
		} catch (SQLException e) {
			log.error("Could not delete wmi country - {0}", e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param region
	 * @return
	 */
	public boolean delete(WMICountry country) {
		if (country == null) {
			return false;
		}
		try {
			countryDao.delete(country);
			log.info("Delete wmi country with name {0} and regex {1},{2}", country.getName(), country.getPrefixRegex(),
					country.getSuffixRegex());
			return true;
		} catch (SQLException e) {
			log.error("Could not delete wmi country - {0}", e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param region
	 * @return
	 */
	public boolean update(WMICountry country) {
		if (country == null) {
			return false;
		}
		try {
			countryDao.update(country);
			log.info("Update wmi country with name {0} and regex {1},{2}", country.getName(), country.getPrefixRegex(),
					country.getSuffixRegex());
			return true;
		} catch (SQLException e) {
			log.error("Could not update wmi country - {0}", e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public WMICountry findById(String id) {
		if (id == null || id.trim().isEmpty()) {
			return null;
		}
		try {
			return countryDao.queryForId(id);
		} catch (SQLException e) {
			log.error("Could not find wmi country - by id {0}", e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<WMICountry> getList() {
		try {
			return countryDao.queryForAll();
		} catch (SQLException e) {
			log.error("Could not load wmi country list - {0}", e.getMessage());
			return Collections.EMPTY_LIST;
		}
	}
}
