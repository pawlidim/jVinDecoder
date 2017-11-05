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
package de.pawlidi.jvindecoder.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents WMI (World Manufacturer Identifier) region of the VIN.
 * The first character of the VIN is the region in which the manufacturer is
 * located.
 * 
 * <ul>
 * <li>Africa - [A-H]</li>
 * <li>Asia - [J-R]</li>
 * <li>Europe - [S-Z]</li>
 * <li>North America - [1-5]</li>
 * <li>Oceania - [6-7]</li>
 * <li>South America - [890]</li>
 * </ul>
 * 
 * @author pawlidim
 *
 */
@DatabaseTable(tableName = WMIRegion.TABLE_NAME)
public class WMIRegion extends BaseModel {

	/** Defines the database table name */
	public static final String TABLE_NAME = "WMI_REGION";
	public static final String NAME = "NAME";
	public static final String REGEX = "WMI_REGEX";

	@DatabaseField(canBeNull = false, columnName = NAME)
	private String name;
	@DatabaseField(canBeNull = false, columnName = REGEX)
	private String wmiRegex;

	/**
	 * Default constructor to construct new WMI region object.
	 */
	public WMIRegion() {
		super();
	}

	/**
	 * @param name
	 * @param wmiRegex
	 */
	public WMIRegion(String name, String wmiRegex) {
		this();
		this.name = name;
		this.wmiRegex = wmiRegex;
	}

	/**
	 * Getter to get the name of WMI region.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter to set the name of WMI region.
	 * 
	 * @param name
	 *            to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter to get the regular expression of the WMI region.
	 * 
	 * @return wmiRegex
	 */
	public String getWmiRegex() {
		return wmiRegex;
	}

	/**
	 * Setter to set the regular expression of the WMI region.
	 * 
	 * @param wmiRegex
	 *            the wmiRegex to set
	 */
	public void setWmiRegex(String wmiRegex) {
		this.wmiRegex = wmiRegex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((wmiRegex == null) ? 0 : wmiRegex.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof WMIRegion)) {
			return false;
		}
		WMIRegion other = (WMIRegion) obj;
		if (name == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!name.equals(other.getName())) {
			return false;
		}
		if (wmiRegex == null) {
			if (other.getWmiRegex() != null) {
				return false;
			}
		} else if (!wmiRegex.equals(other.getWmiRegex())) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "<" + wmiRegex + ">";
	}

}
