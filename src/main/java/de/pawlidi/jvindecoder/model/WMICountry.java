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
 * This class represents WMI (World Manufacturer Identifier) country of the VIN.
 * The first two character of the VIN is the country in which the manufacturer
 * is located.
 * 
 * @author pawlidim
 *
 */
@DatabaseTable(tableName = WMICountry.TABLE_NAME)
public class WMICountry extends BaseModel {

	/** Defines the database table name */
	public static final String TABLE_NAME = "WMI_COUNTRY";
	/** Defines the database column name */
	public static final String NAME = "NAME";
	/** Defines the database column prefix regex */
	public static final String PREFIX_REGEX = "WMI_PREFIX_REGEX";
	/** Defines the database column suffix regex */
	public static final String SUFFIX_REGEX = "WMI_SUFFIX_REGEX";

	@DatabaseField(canBeNull = false, columnName = NAME)
	private String name;
	@DatabaseField(canBeNull = false, columnName = PREFIX_REGEX)
	private String prefixRegex;
	@DatabaseField(canBeNull = false, columnName = SUFFIX_REGEX)
	private String suffixRegex;

	/**
	 * Default constructor to construct new WMI country object.
	 */
	public WMICountry() {
		super();
	}

	/**
	 * @param name
	 * @param prefixRegex
	 * @param suffixRegex
	 */
	public WMICountry(String name, String prefixRegex, String suffixRegex) {
		this();
		this.name = name;
		this.prefixRegex = prefixRegex;
		this.suffixRegex = suffixRegex;
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
	 * @return the prefixRegex
	 */
	public String getPrefixRegex() {
		return prefixRegex;
	}

	/**
	 * @param prefixRegex
	 *            the prefixRegex to set
	 */
	public void setPrefixRegex(String prefixRegex) {
		this.prefixRegex = prefixRegex;
	}

	/**
	 * @return the suffixRegex
	 */
	public String getSuffixRegex() {
		return suffixRegex;
	}

	/**
	 * @param suffixRegex
	 *            the suffixRegex to set
	 */
	public void setSuffixRegex(String suffixRegex) {
		this.suffixRegex = suffixRegex;
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
		result = prime * result + ((prefixRegex == null) ? 0 : prefixRegex.hashCode());
		result = prime * result + ((suffixRegex == null) ? 0 : suffixRegex.hashCode());
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
		if (!(obj instanceof WMICountry)) {
			return false;
		}
		WMICountry other = (WMICountry) obj;
		if (name == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!name.equals(other.getName())) {
			return false;
		}
		if (prefixRegex == null) {
			if (other.getPrefixRegex() != null) {
				return false;
			}
		} else if (!prefixRegex.equals(other.getPrefixRegex())) {
			return false;
		}
		if (suffixRegex == null) {
			if (other.getSuffixRegex() != null) {
				return false;
			}
		} else if (!suffixRegex.equals(other.getSuffixRegex())) {
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
		return name + "<" + prefixRegex + suffixRegex + ">";
	}

}
