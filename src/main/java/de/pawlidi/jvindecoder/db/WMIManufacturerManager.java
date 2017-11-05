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

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import de.pawlidi.jvindecoder.model.WMIManufacturer;
import de.pawlidi.utils.log.ILog;
import de.pawlidi.utils.log.Logging;

public final class WMIManufacturerManager {

	private static final ILog log = Logging.getLog(WMIManufacturerManager.class);

	private final Dao<WMIManufacturer, String> manufacturerDao;
	private final QueryBuilder<WMIManufacturer, String> manufacturerQueryBuilder;

	WMIManufacturerManager(ConnectionSource connection) throws SQLException {
		super();
		try {
			TableUtils.createTable(connection, WMIManufacturer.class);
		} catch (SQLException e) {
			log.error("Could not create table {0}", e.getMessage());
		}
		manufacturerDao = DaoManager.createDao(connection, WMIManufacturer.class);
		manufacturerQueryBuilder = manufacturerDao.queryBuilder();
	}

}
