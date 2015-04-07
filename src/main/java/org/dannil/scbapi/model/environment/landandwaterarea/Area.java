package org.dannil.scbapi.model.environment.landandwaterarea;

import java.util.Arrays;
import java.util.List;

public final class Area extends AbstractLandAndWaterAreaModel {

	private String type;
	private Double squareKm;

	public Area() {
		super();
	}

	public Area(String region, String type, Integer year, Double squareKm) {
		super(region, year);
		this.type = type;
		this.squareKm = squareKm;
	}

	public final String getType() {
		return this.type;
	}

	public final void setType(String type) {
		this.type = type;
	}

	public final Double getSquareKm() {
		return this.squareKm;
	}

	public final void setSquareKm(Double squareKm) {
		this.squareKm = squareKm;
	}

	@Override
	public String toString() {
		return "Area [region=" + super.region + ", year=" + super.year + ", type=" + this.type + ", squareKm=" + this.squareKm + "]";
	}

	public static List<String> getCodes() {
		return Arrays.asList("Region", "ArealTyp", "ContentsCode", "Tid");
	}

	public enum Type {
		LANDAREA("01"), INLANDWATEREXCLUDINGTHEFOURLARGELAKES("02"), THEFOURLARGELAKES("03"), SEAWATER("04"), NULL(null);

		private final String value;

		private Type(String value) {
			this.value = value;
		}

		public static Type of(Integer value) {
			for (Type type : values()) {
				if (type.value != null && type.value.equals(value)) {
					return type;
				}
				return NULL;
			}
			throw new IllegalArgumentException(value.toString());
		}

		@Override
		public String toString() {
			return (this.value != null ? this.value.toString() : null);
		}
	}

}
