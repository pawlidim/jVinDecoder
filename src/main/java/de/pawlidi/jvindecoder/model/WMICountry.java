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

/**
 * https://en.wikibooks.org/wiki/Vehicle_Identification_Numbers_(VIN_codes)/World_Manufacturer_Identifier_(WMI)
 * @author pawlidim
 *
 */
public enum WMICountry {
	SouthAfrica("[A][A-H]"),
    IvoryCoast("[A][J-N]"),
    Africa("([A][P-Z0-9])|([B-D][S-Z0-9])|([E-F][L-Z0-9])|([G-H][A-Z0-9])"),
    Angola("[B][A-E]"),
    Kenya("[B][F-K]"),
    Tanzania("[B][L-R]"),
    Benin("[C][A-E]"),
    Madagascar("[C][F-K]"),
    Tunisia("[C][L-R]"),
    Egypt("[D][A-E]"),
    Morocco("[D][F-H]"),
    Zambia("[D][L-R]"),
    Ghana("[E][A-E]"),
    Mozambique("[E][F-H]"),
    India("([F][A-E])|([M][A-E])"),
    Nigeria("[F][F-K]"),
    Japan("[J][A-Z0-9]"),
    SriLanka("[K][A-E]"),
    Israel("[K][F-K]"),
    SouthKorea("[K][L-R]"),
    Kazakhstan("[K][S-Z0-9]"),
    China("[L][A-Z0-9]"),
    Indonesia("[M][F-K]"),
    Thailand("[M][L-R]"),
    Iran("[N][A-E]"),
    Pakistan("[N][F-K]"),
    Turkey("[N][L-R]"),
    Asia("[M-P][S-Z0-9]"),
    Philippines("[P][A-E]"),
    Singapore("[P][F-K]"),
    Malaysia("[P][L-R]"),
    UAE("[R][A-E]"),
    Taiwan("[R][F-K]"),
    Vietnam("[R][L-R]"),
    SaudiArabia("[R][S-Z0-9]"),
    UnitedKingdom("[S][A-M]"),
    EastGermany("[S][N-T]"),
    Poland("[S][U-Z]"),
    Latvia("[S][1-4]"),
    Europe("([S][5-90])|([T][2-90])|([U][A-G])|([U][1-4])|([U][8-90])|([Z][S-W])|([Z][6-90])"),
    Switzerland("[T][A-H]"),
    CzechRepublic("[T][J-P]"),
    Hungary("[T][R-V]"),
    Portugal("[T][W-Z1]"),
    Denmark("[U][H-M]"),
    Ireland("[U][N-T]"),
    Romania("[U][U-Z]"),
    Slovakia("[U][5-7]"),
    Austria("[V][A-E]"),
    France("[V][F-R]"),
    Spain("[V][S-W]"),
    Serbia("[V][X-Z1-2]"),
    Croatia("[V][3-5]"),
    Estonia("[V][6-90]"),
    Germany("[W][A-Z0-9]"),
    Bulgaria("[X][A-E]"),
    Greece("[X][F-K]"),
    Netherlands("[X][L-R]"),
    USSR("[X][S-W]"),
    Luxembourg("[X][X-Z1-2]"),
    Russia("[X][3-90]"),
    Belgium("[Y][A-E]"),
    Finland("[Y][F-K]"),
    Malta("[Y][L-R]"),
    Sweden("[Y][S-W]"),
    Norway("[Y][X-Z1-2]"),
    Belarus("[Y][3-5]"),
    Ukraine("[Y][6-90]"),
    Italy("[Z][A-R]"),
    Slovenia("[Z][X-Z1-2]"),
    Lithuania("[Z][3-5]"),
    UnitedStates("([1][A-Z0-9])|([4][A-Z0-9])|([5][A-Z0-9])"),
    Canada("[2][A-Z0-9]"),
    Mexico("[3][A-Z0-9]"),
    Australia("[6][A-W]"),
    Oceania("([6][X-Z0-9])|([7][F-Z0-9])"),
    NewZealand("[7][A-E]"),
    Argentina("[8][A-E]"),
    Chile("[8][F-K]"),
    Ecuador("[8][L-R]"),
    Peru("[8][S-W]"),
    Venezuela("[8][X-Z1-2]"),
    SouthAmerica("([8][2-90])|([9][0])|([0][A-Z0-9])"),
    Brazil("([9][A-E])|([9][3-9])"),
    Colombia("[9][F-K]"),
    Paraguay("[9][L-R]"),
    Uruguay("[9][S-W]"),
    Trinidad_Tobago("[9][X-Z1-2]");

    private final String regex;

    WMICountry(final String regex){
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
