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

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

//https://en.wikibooks.org/wiki/Vehicle_Identification_Numbers_(VIN_codes)/World_Manufacturer_Identifier_(WMI)
//https://en.wikipedia.org/wiki/Vehicle_identification_number

/**
 * 
 * @author PAWLIDIM
 *
 */
@DatabaseTable(tableName = WMIManufacturer.NAME)
public class WMIManufacturer extends BaseModel {

	/** Defines the database table name */
	static final String NAME = "WMI_MANUFACTURER";

	@DatabaseField(canBeNull = false)
	private String name;
	@DatabaseField(canBeNull = true)
	private String description;
	@DatabaseField(canBeNull = false)
	private String wmiCode;
	@ForeignCollectionField(eager = false)
	private ForeignCollection<VehicleDescriptorSection> vdsSet;
	@ForeignCollectionField(eager = false)
	private ForeignCollection<VehicleIndicatorSection> visSet;

	/**
	 * Default constructor to construct the WMI manufacturer object.
	 */
	public WMIManufacturer() {
		super();
	}

	/**
	 * Default constructor to construct the WMI manufacturer object with given name,
	 * description and wmi code.
	 * 
	 * @param name
	 * @param description
	 * @param wmiCode
	 */
	public WMIManufacturer(final String name, final String description, final String wmiCode) {
		this.name = name;
		this.description = description;
		this.wmiCode = wmiCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the wmiCode
	 */
	public String getWmiCode() {
		return wmiCode;
	}

	/**
	 * @param wmiCode
	 *            the wmiCode to set
	 */
	public void setWmiCode(String wmiCode) {
		this.wmiCode = wmiCode;
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
		result = prime * result + ((wmiCode == null) ? 0 : wmiCode.hashCode());
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
		if (!(obj instanceof WMIManufacturer)) {
			return false;
		}
		WMIManufacturer other = (WMIManufacturer) obj;
		if (name == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!name.equals(other.getName())) {
			return false;
		}
		if (wmiCode == null) {
			if (other.getWmiCode() != null) {
				return false;
			}
		} else if (!wmiCode.equals(other.getWmiCode())) {
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
		return name + "<" + wmiCode + ">";
	}

}
