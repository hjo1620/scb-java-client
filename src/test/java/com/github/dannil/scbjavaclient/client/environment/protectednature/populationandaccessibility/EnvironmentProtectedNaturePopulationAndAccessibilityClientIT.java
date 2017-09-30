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

package com.github.dannil.scbjavaclient.client.environment.protectednature.populationandaccessibility;

import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.github.dannil.scbjavaclient.client.SCBClient;
import com.github.dannil.scbjavaclient.test.runner.Date;
import com.github.dannil.scbjavaclient.test.runner.DateJUnitRunner;
import com.github.dannil.scbjavaclient.test.utility.RemoteIntegrationTestSuite;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DateJUnitRunner.class)
public class EnvironmentProtectedNaturePopulationAndAccessibilityClientIT extends RemoteIntegrationTestSuite {

    private EnvironmentProtectedNaturePopulationAndAccessibilityClient client;

    @Before
    public void setup() {
        this.client = new SCBClient().environment().protectedNature().populationAndAccessibility();
    }

    @Test
    @Date("2017-09-16")
    public void getProtectedNatureWithinAndAroundLocalities() {
        assertNotEquals(0, this.client.getProtectedNatureWithinAndAroundLocalities().size());
    }

    @Test
    @Date("2017-09-16")
    public void getProtectedNatureWithinAndAroundLocalitiesWithParametersEmptyLists() {
        assertNotEquals(0, this.client.getProtectedNatureWithinAndAroundLocalities(Collections.<String>emptyList(),
                Collections.<Integer>emptyList()).size());
    }

    @Test
    @Date("2017-09-16")
    public void getProtectedNatureWithinAndAroundLocalitiesWithParameters() {
        List<String> regions = Arrays.asList("T0240", "T0740");
        List<Integer> years = Arrays.asList(2013, 2014);

        assertNotEquals(0, this.client.getProtectedNatureWithinAndAroundLocalities(regions, years).size());
    }

    @Test
    @Date("2017-09-16")
    public void getPopulationWithinZoneAroundProtectedNature() {
        assertNotEquals(0, this.client.getPopulationWithinZoneAroundProtectedNature().size());
    }

    @Test
    @Date("2017-09-16")
    public void getPopulationWithinZoneAroundProtectedNatureWithParametersEmptyLists() {
        assertNotEquals(0, this.client.getPopulationWithinZoneAroundProtectedNature(Collections.<String>emptyList(),
                Collections.<Integer>emptyList()).size());
    }

    @Test
    @Date("2017-09-16")
    public void getPopulationWithinZoneAroundProtectedNatureWithParameters() {
        List<String> regions = Arrays.asList("0512", "0563");
        List<Integer> years = Arrays.asList(2013, 2014);

        assertNotEquals(0, this.client.getPopulationWithinZoneAroundProtectedNature(regions, years).size());
    }

}