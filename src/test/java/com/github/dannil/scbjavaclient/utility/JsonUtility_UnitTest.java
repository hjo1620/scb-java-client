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

package com.github.dannil.scbjavaclient.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.dannil.scbjavaclient.exception.SCBClientParsingException;
import com.github.dannil.scbjavaclient.model.Value;
import com.github.dannil.scbjavaclient.model.population.statistic.Population;

@RunWith(JUnit4.class)
public class JsonUtility_UnitTest {

	private String json;

	public JsonUtility_UnitTest() {
		this.json = "{\"query\": [{\"code\": \"ContentsCode\",\"selection\": {\"filter\": \"item\",\"values\": [\"MI0802AA\"]}}],\"response\": {\"format\": \"json\"}}";
	}

	@Test
	public void callPrivateConstructor() throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Constructor<?>[] cons = JsonUtility.class.getDeclaredConstructors();
		cons[0].setAccessible(true);
		cons[0].newInstance();
		cons[0].setAccessible(false);

		assertFalse(cons[0].isAccessible());
	}

	@Test
	public void getNodeField() {
		JsonNode node = JsonUtility.getNode(this.json, "query");

		String comparison = "[{\"code\": \"ContentsCode\",\"selection\": {\"filter\": \"item\",\"values\": [\"MI0802AA\"]}}]";

		assertEquals(node.toString().replaceAll("\\s+", ""), comparison.replaceAll("\\s+", ""));
	}

	@Test
	public void jsonToListNonConventionalJson() {
		String nonConventionalJson = "{\"columns\":[{\"code\":\"Region\",\"text\":\"region\",\"type\":\"d\"},{\"code\":\"Civilstand\",\"text\":\"marital status\",\"type\":\"d\"},{\"code\":\"Alder\",\"text\":\"age\",\"type\":\"d\"},{\"code\":\"Tid\",\"text\":\"year\",\"type\":\"t\"},{\"code\":\"BE0101N1\",\"text\":\"Population\",\"comment\":\"The tables show the conditions on December 31st for each respective year according to administrative subdivisions of January 1st of the following year\\r\\n\",\"type\":\"c\"},{\"code\":\"BE0101N2\",\"text\":\"Population growth\",\"comment\":\"Population growth is defined as the difference between the population at the beginning of the year and at the end of the year.\\r\\n\",\"type\":\"c\"}],\"comments\":[],\"data\":[{\"key\":[\"00\",\"OG\",\"45\",\"2011\"],\"values\":[\"48403\",\"1007\"]}]}";

		JsonNode node = JsonUtility.getNode(nonConventionalJson);

		List<Population> convertedPopulations = JsonUtility.jsonToListOf(node, Population.class);

		List<Value<String>> values = new ArrayList<Value<String>>();

		Value<String> value1 = new Value<String>("48403", "BE0101N1", "Population");
		values.add(value1);

		Value<String> value2 = new Value<String>("1007", "BE0101N2", "Population growth");
		values.add(value2);

		Population p = new Population("00", "OG", "45", null, 2011, values);
		List<Population> staticPopulations = Arrays.asList(p);

		assertEquals(convertedPopulations, staticPopulations);
	}

	@Test(expected = SCBClientParsingException.class)
	public void jsonToListOfInvalidJson() {
		JsonNode node = JsonUtility.getNode(this.json);

		List<Population> populations = JsonUtility.jsonToListOf(node, Population.class);

		assertNull(populations);
	}

	@Test(expected = SCBClientParsingException.class)
	public void getNodeInvalidJson() {
		JsonNode node = JsonUtility.getNode("hello world");

		assertNull(node);
	}

}
