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

package com.github.dannil.scbjavaclient.client.businessactivities.industrialinventories.inventories;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.github.dannil.scbjavaclient.test.extensions.Date;
import com.github.dannil.scbjavaclient.test.extensions.Remote;
import com.github.dannil.scbjavaclient.test.extensions.Suite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Suite
@Remote
public class BusinessActivitiesIndustrialInventoriesInventoriesClientIT {

    private BusinessActivitiesIndustrialInventoriesInventoriesClient client;

    @BeforeEach
    public void setup() {
        this.client = new BusinessActivitiesIndustrialInventoriesInventoriesClient();
    }

    @Test
    @Date("2017-04-13")
    public void getChangesInInventories() {
        assertNotEquals(0, this.client.getChangesInInventories().size());
    }

    @Test
    @Date("2017-04-13")
    public void getChangesInInventoriesWithParametersEmptyLists() {
        assertNotEquals(0, this.client.getChangesInInventories(Collections.<String>emptyList(),
                Collections.<String>emptyList(), Collections.<String>emptyList()).size());
    }

    @Test
    @Date("2017-04-13")
    public void getChangesInInventoriesWithParameters() {
        List<String> inventoryTypes = Arrays.asList("INSATS", "VARARB");
        List<String> industrialClassifications = Arrays.asList("IVKON", "C");
        List<String> quarters = Arrays.asList("2015K2", "2015K4");

        assertNotEquals(0,
                this.client.getChangesInInventories(inventoryTypes, industrialClassifications, quarters).size());
    }

}
