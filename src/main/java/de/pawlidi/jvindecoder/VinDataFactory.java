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

import java.util.regex.Pattern;

import de.pawlidi.jvindecoder.model.WMICountry;
import de.pawlidi.jvindecoder.model.WMIRegion;
import de.pawlidi.jvindecoder.utils.Utils;

/**
 * @author pawlidim
 *
 */
public final class VinDataFactory {

	public static boolean isValidVIN(String code) {
		return Utils.isNotBlank(code) && code.trim().length() == 17;
	}

	public static String extractWMI(String code) {
		return Utils.truncate(code, 3);
	}

	public static WMIRegion decodeRegion(String code) {
		if (!isValidVIN(code)) {
			return null;
		}
		final String region = Utils.truncate(code, 1);
		for (WMIRegion wmiRegion : WMIRegion.values()) {
			if (Pattern.matches(wmiRegion.getRegex(), region)) {
				return wmiRegion;
			}
		}
		return null;
	}

	public static WMICountry decodeCountry(String code) {
		if (!isValidVIN(code)) {
			return null;
		}
		final String region = Utils.truncate(code, 1);
		for (WMICountry wmiCountry : WMICountry.values()) {
			if (Pattern.matches(wmiCountry.getRegex(), region)) {
				return wmiCountry;
			}
		}
		return null;
	}
}
