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

package com.github.dannil.scbjavaclient.client.livingconditions.families.siblings;

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
public class SiblingsClientIT extends RemoteIntegrationTestSuite {

    private SiblingsClient client;

    @Before
    public void setup() {
        this.client = new SCBClient().livingConditions().families().siblings();
    }

    @Test
    public void getSiblings() {
        assertNotEquals(0, this.client.getSiblings().size());
    }

    @Test
    public void getSiblingsWithParametersEmptyLists() {
        assertNotEquals(0, this.client.getSiblings(Collections.<String>emptyList(), Collections.<String>emptyList(),
                Collections.<Integer>emptyList(), Collections.<String>emptyList(), Collections.<Integer>emptyList()));
    }

    @Test
    public void getSiblingsWithParameters() {
        List<String> sexes = Arrays.asList("5", "6");
        List<String> ages = Arrays.asList("0-17");
        List<Integer> siblingsLivingAtHome = Arrays.asList(50, 70);
        List<String> familyTypes = Arrays.asList("Annan");
        List<Integer> years = Arrays.asList(2015);

        assertNotEquals(0, this.client.getSiblings(sexes, ages, siblingsLivingAtHome, familyTypes, years).size());
    }

}