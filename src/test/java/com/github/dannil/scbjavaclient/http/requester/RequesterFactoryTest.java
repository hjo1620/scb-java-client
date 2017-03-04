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

package com.github.dannil.scbjavaclient.http.requester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

import com.github.dannil.scbjavaclient.http.RequestMethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RequesterFactoryTest {

    @Test
    public void callPrivateConstructor()
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?>[] cons = RequesterFactory.class.getDeclaredConstructors();
        cons[0].setAccessible(true);
        cons[0].newInstance();
        cons[0].setAccessible(false);

        assertFalse(cons[0].isAccessible());
    }

    @Test
    public void getGETRequester() {
        AbstractRequester get = RequesterFactory.getRequester(RequestMethod.GET);

        assertEquals(StandardCharsets.UTF_8, get.getCharset());
    }

    @Test
    public void getGETRequesterWithCharset() {
        AbstractRequester get = RequesterFactory.getRequester(RequestMethod.GET, StandardCharsets.US_ASCII);

        assertEquals(StandardCharsets.US_ASCII, get.getCharset());
    }

    @Test
    public void getPOSTRequester() {
        AbstractRequester post = RequesterFactory.getRequester(RequestMethod.POST);

        assertEquals(StandardCharsets.UTF_8, post.getCharset());
    }

    @Test
    public void getPOSTRequesterWithCharset() {
        AbstractRequester post = RequesterFactory.getRequester(RequestMethod.POST, StandardCharsets.US_ASCII);

        assertEquals(StandardCharsets.US_ASCII, post.getCharset());
    }

}
