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

package com.github.dannil.scbjavaclient.client.labourmarket.grosspay.aggregatewages;

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
public class LabourMarketGrossPayAggregateWagesClientIT extends RemoteIntegrationTestSuite {

    private LabourMarketGrossPayAggregateWagesClient client;

    @Before
    public void setup() {
        this.client = new SCBClient().labourMarket().grossPay().aggregateWages();
    }

    @Test
    public void getGrossPay() {
        assertNotEquals(0, this.client.getGrossPay().size());
    }

    @Test
    public void getGrossPayWithParametersEmptyLists() {
        assertNotEquals(0, this.client.getGrossPay(Collections.<String>emptyList(), Collections.<Integer>emptyList()));
    }

    @Test
    public void getGrossPayWithParameters() {
        List<String> counties = Arrays.asList("05", "13");
        List<Integer> years = Arrays.asList(2002, 2005);

        assertNotEquals(0, this.client.getGrossPay(counties, years).size());
    }

}
