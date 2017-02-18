/*
 * Copyright 2016 Daniel Nilsson
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.github.dannil.scbjavaclient.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import com.github.dannil.scbjavaclient.test.RemoteIntegrationTestSuite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SCBClientIT extends RemoteIntegrationTestSuite {

    @Test
    public void getInputs() {
        Locale locale = new Locale("sv", "SE");
        SCBClient client = new SCBClient(locale);

        Map<String, Collection<String>> inputs = client.getInputs("UF/UF0104/SamAntSkol");

        Map<String, Collection<String>> staticInputs = new HashMap<String, Collection<String>>();
        Collection<String> staticInputsArsKurs = Arrays.asList("10", "11", "12", "13", "14", "15", "16");
        Collection<String> staticInputsContentsCode = Arrays.asList("UF0104L1");
        Collection<String> staticInputsTid = Arrays.asList("1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006");

        staticInputs.put("ArsKurs", staticInputsArsKurs);
        staticInputs.put("ContentsCode", staticInputsContentsCode);
        staticInputs.put("Tid", staticInputsTid);

        assertEquals(staticInputs, inputs);
    }

    @Test
    public void getRawData() {
        Locale locale = new Locale("sv", "SE");
        SCBClient client = new SCBClient(locale);

        String response = client.getRawData("BE/BE0101/BE0101A/BefolkningNy");

        assertTrue(response.contains("BE0101N1"));
        assertTrue(response.contains("BE0101N2"));
        assertTrue(response.contains("Region"));
        assertTrue(response.contains("Tid"));
    }

    @Test
    public void getRawDataInputs() {
        Locale locale = new Locale("sv", "SE");
        SCBClient client = new SCBClient(locale);

        Map<String, Collection<?>> payload = new HashMap<String, Collection<?>>();
        payload.put("ContentsCode", Arrays.asList("BE0101N1"));
        payload.put("Region", Arrays.asList("00", "01", "0114"));
        payload.put("Civilstand", Arrays.asList("OG", "G"));
        payload.put("Alder", Arrays.asList(45, 50));
        payload.put("Tid", Arrays.asList(2011, 2012));

        String response = client.getRawData("BE/BE0101/BE0101A/BefolkningNy", payload);

        assertTrue(response.contains("Region"));
        assertTrue(response.contains("Civilstand"));
        assertTrue(response.contains("Alder"));
        assertTrue(response.contains("Tid"));
    }

    @Test
    public void getConfig() {
        Locale locale = new Locale("sv", "SE");
        SCBClient client = new SCBClient(locale);

        Map<String, String> config = client.getConfig();
        assertFalse(config.isEmpty());
        for (Entry<String, String> entry : config.entrySet()) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
        }
    }

    @Test
    public void getConfigValue() {
        Locale locale = new Locale("sv", "SE");
        SCBClient client = new SCBClient(locale);

        assertNotNull(client.getConfigValue("maxCalls"));
    }

    @Test
    public void getRegions() {
        SCBClient client = new SCBClient();

        List<String> regions = client.getRegions("BE/BE0101/BE0101A/BefolkningNy");

        assertNotNull(regions);
        assertFalse(regions.isEmpty());
    }

    public void getRegionsMissingCodeInTable() {
        SCBClient client = new SCBClient();

        List<String> regions = client.getRegions("BE/BE0001/BE0001T04Ar");

        assertNull(regions);
    }

    @Test
    public void getYears() {
        SCBClient client = new SCBClient();

        List<String> years = client.getYears("BE/BE0101/BE0101A/BefolkningNy");

        assertNotNull(years);
        assertFalse(years.isEmpty());
    }

    public void getYearsMissingCodeInTable() {
        SCBClient client = new SCBClient();

        List<String> years = client.getYears("NR/NR0105/NR0105A");

        assertNull(years);
    }

    @Test
    public void supportedLocale() {
        Locale locale = new Locale("sv", "SE");

        assertTrue(SCBClient.isSupportedLocale(locale));
    }

    @Test
    public void unsupportedLocale() {
        Locale locale = new Locale("fr", "CA");

        assertFalse(SCBClient.isSupportedLocale(locale));
    }

}
