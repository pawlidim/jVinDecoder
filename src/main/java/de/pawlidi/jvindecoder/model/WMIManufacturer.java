/*
 * Copyright (C) 2016 Maximilian Pawlidi
 *
 */
package de.pawlidi.jvindecoder.model;

//https://en.wikibooks.org/wiki/Vehicle_Identification_Numbers_(VIN_codes)/World_Manufacturer_Identifier_(WMI)
//https://en.wikipedia.org/wiki/Vehicle_identification_number
public enum WMIManufacturer {

	Volkswagen("(AAV)|(LFV)|(LSV)|(WVG)|(WVW)|(WV1)|(WV2)|(1VW)|(3VW)|(8AW)|(9BW)"), 
	Hyundai("(AHT)|(JT)|(LFM)|(LTV)|(LVG)|(NMT)|(SB1)|(VNK)|(2T)|(4T)|(5T)|(6T1)|(8AJ)|(9BR)"), 
	Ford("(AFA)|(LVS)|(MAJ)|(MNB)|(NM0)|(SFA)|(VS6)|(PE1)|(WF0)|(1F)|(2F)|(3F)|(6F)|(8AF)|(9BF)|(1FA)|(1FB)|(1FC)|(1FD)|(1FM)|(1FT)|(1ZV)|(2FA)|(2FB)|(2FC)|(2FM)|(2FT)|(3FA)|(3FE)|(6FP)|(9BF)"), 
	Toyota("T|M|G");

	private final String regex;

	WMIManufacturer(final String regex) {
		this.regex = regex;
	}

	/**
	 * 
	 * @return
	 */
	public String getRegex() {
		return regex;
	}
}
