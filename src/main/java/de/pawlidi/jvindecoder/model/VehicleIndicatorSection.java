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
 * 
 * @author PAWLIDIM
 *
 */
@DatabaseTable(tableName = VehicleIndicatorSection.NAME)
public class VehicleIndicatorSection extends BaseModel {

	/** Defines the database table name */
	static final String NAME = "VIS";
	static final String MANUFACTURER = "MANUFACTURER_ID";

	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true, columnName = MANUFACTURER)
	protected WMIManufacturer manufacturer;
	@DatabaseField(canBeNull = true)
	protected String code;
	@DatabaseField(canBeNull = true)
	protected String description;

	/**
	 * Default constructor to construct the Vehicle Descriptor Section object.
	 */
	public VehicleIndicatorSection() {
		super();
	}

	/**
	 * @return the manufacturer
	 */
	public WMIManufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(WMIManufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
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
		if (!(obj instanceof VehicleDescriptorSection)) {
			return false;
		}
		VehicleDescriptorSection other = (VehicleDescriptorSection) obj;
		if (code == null) {
			if (other.getCode() != null) {
				return false;
			}
		} else if (!code.equals(other.getCode())) {
			return false;
		}
		if (description == null) {
			if (other.getDescription() != null) {
				return false;
			}
		} else if (!description.equals(other.getDescription())) {
			return false;
		}
		if (manufacturer == null) {
			if (other.getManufacturer() != null) {
				return false;
			}
		} else if (!manufacturer.equals(other.getManufacturer())) {
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
		return manufacturer + "<" + code + "|" + description + ">";
	}

}
