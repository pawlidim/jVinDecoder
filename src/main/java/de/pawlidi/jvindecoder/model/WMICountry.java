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
 * 
 * @author pawlidim
 *
 */
public enum WMICountry {
	SouthAfrica("[A][A-H]"),
    IvoryCoast("[A][J-N]"),
    Africa("([A][P-Z0-9])([B-D][S-Z0-9])([E-F][L-Z0-9])([G-H][A-Z0-9])"),
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
    India("([F][A-E])([M][A-E])"),
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
    Europe("([S][5-0])([T][2-0])([U][A-G])([U][1-4])([U][8-0])"),
    Switzerland("[T][A-H]"),
    CzechRepublic("[T][J-P]"),
    Hungary("[T][R-V]"),
    Portugal("[T][W-Z1]"),
    Denmark("[T][H-M]"),
    Ireland("[T][N-T]"),
    Romania("[T][U-Z]"),
    Slovakia("[T][5-7]"),
    Austria("[V][A-E]"),
    France("[V][F-R]"),
    Spain("[V][S-W]"),
    Serbia("[V][X-Z0-9]"),
    Croatia("[V][3-5]"),
    Estonia("[V][6-9]"),
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
    Norway("[Y][X-Z0-9]"),
    Belarus("[Y][]"),
    Ukraine("[][]"),
    Italy("[][]"),
    Slovenia("[][]"),
    Lithuania("[][]"),
    UnitedStates("[][]"),
    Canada("[][]"),
    Mexico("[][]"),
    Australia("[][]"),
    Oceania("[][]"),
    NewZealand("[][]"),
    Argentina("[][]"),
    Chile("[][]"),
    Ecuador("[][]"),
    Peru("[][]"),
    Venezuela("[][]"),
    SouthAmerica("[][]"),
    Brazil("[][]"),
    Colombia("[][]"),
    Paraguay("[][]"),
    Uruguay("[][]"),
    Trinidad_Tobago("[][]");

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
