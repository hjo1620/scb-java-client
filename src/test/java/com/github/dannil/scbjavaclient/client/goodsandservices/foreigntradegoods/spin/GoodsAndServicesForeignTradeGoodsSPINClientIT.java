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

package com.github.dannil.scbjavaclient.client.goodsandservices.foreigntradegoods.spin;

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
public class GoodsAndServicesForeignTradeGoodsSPINClientIT {

    private GoodsAndServicesForeignTradeGoodsSPINClient client;

    @BeforeEach
    public void setup() {
        this.client = new GoodsAndServicesForeignTradeGoodsSPINClient();
    }

    @Test
    @Date("2017-04-28")
    public void getImportsAndExportsOfGoods() {
        assertNotEquals(0, this.client.getImportsAndExportsOfGoods().size());
    }

    @Test
    @Date("2017-04-28")
    public void getImportsAndExportsOfGoodsWithParametersEmptyLists() {
        assertNotEquals(0, this.client.getImportsAndExportsOfGoods(Collections.<String>emptyList(),
                Collections.<Integer>emptyList()).size());
    }

    @Test
    @Date("2017-04-28")
    public void getImportsAndExportsOfGoodsWithParameters() {
        List<String> spin2007 = Arrays.asList("A-U", "01");
        List<Integer> years = Arrays.asList(2010, 2011);

        assertNotEquals(0, this.client.getImportsAndExportsOfGoods(spin2007, years).size());
    }

}
