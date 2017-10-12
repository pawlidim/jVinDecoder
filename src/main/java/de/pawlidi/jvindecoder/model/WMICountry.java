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
    Africa("[A][P-Z0-9]"),
    Angola("[B][A-E]"),
    Kenya("[B][F-K]"),
    Tanzania("[B][]"),
    Benin("[B][]"),
    Madagascar("[B][]"),
    Tunisia("[B][]"),
    Egypt("[B][]"),
    Morocco("[][]"),
    Zambia("[][]"),
    Ghana("[][]"),
    Mozambique("[][]"),
    India("[][]"),
    Nigeria("[][]"),
    Japan("[][]"),
    SriLanka("[][]"),
    Israel("[][]"),
    SouthKorea("[][]"),
    Kazakhstan("[][]"),
    China("[][]"),
    Indonesia("[][]"),
    Thailand("[][]"),
    Iran("[][]"),
    Pakistan("[][]"),
    Turkey("[][]"),
    Asia("[][]"),
    Philippines("[][]"),
    Singapore("[][]"),
    Malaysia("[][]"),
    UAE("[][]"),
    Taiwan("[][]"),
    Vietnam("[][]"),
    SaudiArabia("[][]"),
    UnitedKingdom("[][]"),
    EastGermany("[][]"),
    Poland("[][]"),
    Latvia("[][]"),
    Europe("[][]"),
    Switzerland("[][]"),
    CzechRepublic("[][]"),
    Hungary("[][]"),
    Portugal("[][]"),
    Denmark("[][]"),
    Ireland("[][]"),
    Romania("[][]"),
    Slovakia("[][]"),
    Austria("[][]"),
    France("[][]"),
    Spain("[][]"),
    Serbia("[][]"),
    Croatia("[][]"),
    Estonia("[][]"),
    Germany("[][]"),
    Bulgaria("[][]"),
    Greece("[][]"),
    Netherlands("[][]"),
    USSR("[][]"),
    Luxembourg("[][]"),
    Russia("[][]"),
    Belgium("[][]"),
    Finland("[][]"),
    Malta("[][]"),
    Sweden("[][]"),
    Norway("[][]"),
    Belarus("[][]"),
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
