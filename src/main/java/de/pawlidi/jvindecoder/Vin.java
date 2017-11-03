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
package de.pawlidi.jvindecoder;

import java.io.Serializable;

import de.pawlidi.jvindecoder.model.VehicleDescriptorSection;
import de.pawlidi.jvindecoder.model.VehicleIndicatorSection;
import de.pawlidi.jvindecoder.model.WMICountry;
import de.pawlidi.jvindecoder.model.WMIManufacturer;
import de.pawlidi.jvindecoder.model.WMIRegion;

/**
 * A Vehicle Identification Number (VIN) is a unique code, including vehicle
 * specific informations. The VIN is composed of the following sections: The
 * first three characters uniquely identify the manufacturer of the vehicle
 * using the world manufacturer identifier or WMI code.The fourth to eighth
 * positions in the VIN are the Vehicle Descriptor Section (VDS). This is used,
 * according to local regulations, to identify the vehicle type, and may include
 * information on the automobile platform used, the model, and the body. The
 * 10th to 17th positions are used as the Vehicle Identifier Section (VIS). This
 * is used by the manufacturer to identify the individual vehicle in question.
 * This may include information on options installed or engine and transmission
 * choices, but often is a simple sequential number.
 * 
 * <p>
 * 
 * <table border="1" style="text-align:center">
 * <tr>
 * <th>Standard</th>
 * <td style="width:5%">1</td>
 * <td style="width:5%">2</td>
 * <td style="width:5%">3</td>
 * <td style="width:5%">4</td>
 * <td style="width:5%">5</td>
 * <td style="width:5%">6</td>
 * <td style="width:5%">7</td>
 * <td style="width:5%">8</td>
 * <td style="width:5%">9</td>
 * <td style="width:5%">10</td>
 * <td style="width:5%">11</td>
 * <td style="width:5%">12</td>
 * <td style="width:5%">13</td>
 * <td style="width:5%">14</td>
 * <td style="width:5%">15</td>
 * <td style="width:5%">16</td>
 * <td style="width:5%">17</td>
 * </tr>
 * <tr>
 * <th>ISO 3779</th>
 * <td colspan="3">WMI</td>
 * <td colspan="6">VDS</td>
 * <td colspan="8">VIS</td>
 * </tr>
 * <tr>
 * <th>North American / EU
 * <p>
 * &gt; 500 vehicles / year
 * </p>
 * </th>
 * <td colspan="3">Manufacturer Identifier</td>
 * <td colspan="5">Vehicle Attributes</td>
 * <td>Check Digit</td>
 * <td>Model Year</td>
 * <td>Plant Code</td>
 * <td colspan="8">Sequential Number</td>
 * </tr>
 * <tr>
 * <th>North American / EU
 * <p>
 * &lt; 500 vehicles / year
 * </p>
 * </th>
 * <td colspan="3">Manufacturer Identifier</td>
 * <td colspan="5">Vehicle Attributes</td>
 * <td>Check Digit</td>
 * <td>Model Year</td>
 * <td>Plant Code</td>
 * <td colspan="3">Manufacturer Identifier</td>
 * <td colspan="3">Sequential Number</td>
 * </tr>
 * </table>
 * 
 * @author pawlidim
 *
 */
public final class Vin implements Serializable {

	private String code;
	private WMICountry country;
	private WMIRegion region;
	private WMIManufacturer manufacturer;
	private VehicleDescriptorSection vds;
	private VehicleIndicatorSection vis;

	/**
	 * Invisible default constructor to construct new vin object.
	 */
	Vin() {
		super();
	}

	/**
	 * Default constructor to construct new vin object with given code.
	 * 
	 * @param code
	 *            with 17 characters
	 */
	public Vin(final String code) {
		this();
		this.code = code;
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
	 * @return the country
	 */
	public WMICountry getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(WMICountry country) {
		this.country = country;
	}

	/**
	 * @return the region
	 */
	public WMIRegion getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(WMIRegion region) {
		this.region = region;
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
	 * @return the vds
	 */
	public VehicleDescriptorSection getVds() {
		return vds;
	}

	/**
	 * @param vds
	 *            the vds to set
	 */
	public void setVds(VehicleDescriptorSection vds) {
		this.vds = vds;
	}

	/**
	 * @return the vis
	 */
	public VehicleIndicatorSection getVis() {
		return vis;
	}

	/**
	 * @param vis
	 *            the vis to set
	 */
	public void setVis(VehicleIndicatorSection vis) {
		this.vis = vis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Vin)) {
			return false;
		}
		Vin other = (Vin) obj;
		if (code == null) {
			if (other.getCode() != null) {
				return false;
			}
		} else if (!code.equals(other.getCode())) {
			return false;
		}
		return true;
	}

}
