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

package com.github.dannil.scbjavaclient.model;

import java.util.Objects;

public class Value<V> {

	private String code;
	private V value;
	private String text;

	public Value() {
		// TODO Auto-generated constructor stub
	}

	public Value(V value) {
		this.value = value;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public V getValue() {
		return this.value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.code, this.value, this.text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Value<?>)) {
			return false;
		}

		Value<?> other = (Value<?>) obj;
		return Objects.equals(this.code, other.code) && Objects.equals(this.value, other.value)
				&& Objects.equals(this.text, other.text);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(128);

		builder.append("Value [code=");
		builder.append(this.code);
		builder.append(", value=");
		builder.append(this.value);
		builder.append(", text=");
		builder.append(this.text);
		builder.append("]");

		return builder.toString();
	}

}
