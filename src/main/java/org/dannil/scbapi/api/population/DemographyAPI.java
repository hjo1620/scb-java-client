package org.dannil.scbapi.api.population;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.dannil.scbapi.api.AbstractAPI;
import org.dannil.scbapi.model.AverageAgeFirstChild;
import org.dannil.scbapi.utility.QueryBuilder;
import org.dannil.scbapi.utility.RequestPoster;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;

public final class DemographyAPI extends AbstractAPI implements DemographyOperations {

	private String url;

	public DemographyAPI() {
		this.locale = Locale.getDefault();

		buildUrl();
	}

	public DemographyAPI(Locale locale) {
		this();
		this.locale = locale;

		buildUrl();
	}

	@Override
	public final void setLocale(Locale locale) {
		this.locale = locale;

		buildUrl();
	}

	public final void buildUrl() {
		this.url = "http://api.scb.se/OV0104/v1/doris/" + this.locale.getLanguage() + "/ssd/BE/BE0701/MedelAlderNY";
	}

	public final List<String> getAvailableRegions() {
		String response = RequestPoster.doGet(this.url);

		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(response);
			List<JsonNode> nodes = node.findValues("values");
			node = nodes.get(0);

			List<String> years = new ArrayList<String>(node.size());
			for (int i = 0; i < node.size(); i++) {
				years.add(node.get(i).asText());
			}
			return years;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public final List<Integer> getAvailableGenders() {
		String response = RequestPoster.doGet(this.url);

		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(response);
			List<JsonNode> nodes = node.findValues("values");
			node = nodes.get(1);

			List<Integer> genders = new ArrayList<Integer>(node.size());
			for (int i = 0; i < node.size(); i++) {
				genders.add(node.get(i).asInt());
			}
			return genders;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public final List<Integer> getAvailableYears() {
		String response = RequestPoster.doGet(this.url);

		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(response);
			List<JsonNode> nodes = node.findValues("values");
			node = nodes.get(nodes.size() - 1);

			List<Integer> years = new ArrayList<Integer>(node.size());
			for (int i = 0; i < node.size(); i++) {
				years.add(node.get(i).asInt());
			}
			return years;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AverageAgeFirstChild> getAverageAgeFirstChild() {
		return this.getAverageAgeFirstChild(null, null, null);
	}

	@Override
	public List<AverageAgeFirstChild> getAverageAgeFirstChild(List<String> regions, List<Integer> genders, List<Integer> years) {
		QueryBuilder<String, String> queryBuilder = new QueryBuilder<String, String>();

		ArrayListMultimap<String, String> map = ArrayListMultimap.create();
		map.put("ContentsCode", "BE0701AB");
		if (regions != null) {
			for (String region : regions) {
				map.put("Region", region);
			}
		}
		if (genders != null) {
			for (Integer gender : genders) {
				map.put("Kon", gender.toString());
			}
		}
		if (years != null) {
			for (Integer year : years) {
				map.put("Tid", year.toString());
			}
		}

		String query = queryBuilder.build(map);
		String response = RequestPoster.doPost(this.url, query);
		return null;
	}

}
