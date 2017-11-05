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

import de.pawlidi.jvindecoder.model.WMIRegion;
import de.pawlidi.utils.log.ILog;
import de.pawlidi.utils.log.Logging;

/**
 * WMI region table manager.
 * 
 * @author PAWLIDIM
 *
 */
public final class WMIRegionManager {

	private static final ILog log = Logging.getLog(WMIRegionManager.class);

	private final Dao<WMIRegion, String> regionDao;
	private final QueryBuilder<WMIRegion, String> regionQueryBuilder;

	/**
	 * Package visible default constructor to construct new wmi region manager.
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	WMIRegionManager(ConnectionSource connection) throws SQLException {
		super();
		try {
			TableUtils.createTable(connection, WMIRegion.class);
		} catch (SQLException e) {
			log.error("Could not create table {0}", e.getMessage());
		}
		regionDao = DaoManager.createDao(connection, WMIRegion.class);
		regionQueryBuilder = regionDao.queryBuilder();
	}

	/**
	 * Creates new wmi region.
	 * 
	 * @param region
	 * @return true, if creation was successful, false otherwise
	 */
	public boolean create(WMIRegion region) {
		if (region == null || region.getName() == null || region.getWmiRegex() == null || region.getUuid() == null) {
			return false;
		}
		try {
			regionDao.create(region);
			log.info("Create new wmi region with name {0} and regex {1}", region.getName(), region.getWmiRegex());
			return true;
		} catch (SQLException e) {
			log.error("Could not create wmi region - {0}", e.getMessage());
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
			regionDao.deleteById(id);
			log.info("Delete new wmi region with id {0} ", id);
			return true;
		} catch (SQLException e) {
			log.error("Could not delete wmi region - {0}", e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param region
	 * @return
	 */
	public boolean delete(WMIRegion region) {
		if (region == null) {
			return false;
		}
		try {
			regionDao.delete(region);
			log.info("Delete wmi region with name {0} and regex {1}", region.getName(), region.getWmiRegex());
			return true;
		} catch (SQLException e) {
			log.error("Could not delete wmi region - {0}", e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param region
	 * @return
	 */
	public boolean update(WMIRegion region) {
		if (region == null) {
			return false;
		}
		try {
			regionDao.update(region);
			log.info("Update wmi region with name {0} and regex {1}", region.getName(), region.getWmiRegex());
			return true;
		} catch (SQLException e) {
			log.error("Could not update wmi region - {0}", e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public WMIRegion findById(String id) {
		if (id == null || id.trim().isEmpty()) {
			return null;
		}
		try {
			return regionDao.queryForId(id);
		} catch (SQLException e) {
			log.error("Could not find wmi region - by id {0}", e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<WMIRegion> getList() {
		try {
			return regionDao.queryForAll();
		} catch (SQLException e) {
			log.error("Could not load wmi region list - {0}", e.getMessage());
			return Collections.EMPTY_LIST;
		}
	}

}
