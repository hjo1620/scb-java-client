/*
 * Copyright 2017 Daniel Nilsson
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

package com.github.dannil.scbjavaclient.client.livingconditions.families.housing;

import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.github.dannil.scbjavaclient.client.SCBClient;
import com.github.dannil.scbjavaclient.test.utility.RemoteIntegrationTestSuite;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HousingClientIT extends RemoteIntegrationTestSuite {

    private HousingClient client;

    @Before
    public void setup() {
        this.client = new SCBClient().livingConditions().families().housing();
    }

    @Test
    public void getHousing() {
        assertNotEquals(0, this.client.getHousing().size());
    }

    @Test
    public void getHousingWithParametersEmptyLists() {
        assertNotEquals(0, this.client.getHousing(Collections.<String>emptyList(), Collections.<String>emptyList(),
                Collections.<String>emptyList(), Collections.<String>emptyList(), Collections.<String>emptyList(),
                Collections.<Integer>emptyList(), Collections.<Integer>emptyList()));
    }

    @Test
    public void getHousingWithParameters() {
        List<String> sexes = Arrays.asList("5", "6");
        List<String> ages = Arrays.asList("18-21");
        List<String> housingTypes = Arrays.asList("HR", "BR");
        List<String> familyTypes = Arrays.asList("EnsamTot", "Annan");
        List<String> backgrounds = Arrays.asList("TotalC");
        List<Integer> parentIncomes = Arrays.asList(30);
        List<Integer> years = Arrays.asList(2015);

        assertNotEquals(0, this.client.getHousing(sexes, ages, housingTypes, familyTypes, backgrounds, parentIncomes,
                years).size());
    }

}