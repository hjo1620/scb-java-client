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

package com.github.dannil.scbjavaclient.utility.requester;

/**
 * Factory for returning regular (non-singleton) requesters.
 * 
 * @author Daniel Nilsson
 */
public class RequesterFactory {

	private RequesterFactory() {

	}

	/**
	 * Returns a regular (non-singleton) requester which matches the method.
	 * 
	 * @param method
	 *            the method (i.e. GET or POST)
	 * @return a regular (non-singleton) Requester which matches the method.
	 */
	public static AbstractRequester getRequester(String method) {
		switch (method.toUpperCase()) {
			case "GET":
				return new GETRequester();

			case "POST":
				return new POSTRequester();

			default:
				throw new IllegalArgumentException(method + " is not a valid method");
		}
	}

}
