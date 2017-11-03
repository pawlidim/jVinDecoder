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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VinDataFactoryTest {

	private static final String VALID_VIN = "W0L000051T2123456";

	@Test
	public void isValidVIN() {
		assertTrue(VinDataFactory.isValidVIN(VALID_VIN));
		assertFalse(VinDataFactory.isValidVIN(VALID_VIN + "cas"));
	}

	@Test
	public void extractWMI() {
		assertNotNull(VinDataFactory.extractWMI(VALID_VIN));
		System.out.println(VinDataFactory.extractWMI(VALID_VIN));
	}

	// @Test
	// public void decodeRegion() {
	// assertNotNull(VinDataFactory.decodeRegion(VALID_VIN));
	// System.out.println(VinDataFactory.decodeRegion(VALID_VIN));
	// }
	//
	// @Test
	// public void decodeCountry() {
	// assertNotNull(VinDataFactory.decodeCountry(VALID_VIN));
	// System.out.println(VinDataFactory.decodeCountry(VALID_VIN));
	// }

}
