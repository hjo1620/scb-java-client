/*
 * Copyright 2016 Daniel Nilsson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.dannil.scbjavaclient.client.environment.landandwaterarea;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EnvironmentLandAndWaterAreaClientTest {

	@Test
	public void createWithLocaleConstructor() {
		Locale locale = new Locale("sv", "SE");
		EnvironmentLandAndWaterAreaClient client = new EnvironmentLandAndWaterAreaClient(locale);

		assertEquals(locale, client.getLocale());
	}

	@Test
	public void getUrl() {
		Locale locale = new Locale("sv", "SE");
		EnvironmentLandAndWaterAreaClient client = new EnvironmentLandAndWaterAreaClient(locale);

		assertEquals("http://api.scb.se/OV0104/v1/doris/sv/ssd/MI/MI0802/", client.getUrl());
	}

}