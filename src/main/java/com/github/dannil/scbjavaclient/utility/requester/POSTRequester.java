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

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;

public class POSTRequester extends AbstractRequester {

	private String payload;

	public POSTRequester() {
		super();
		super.requestProperties.put("Request-Method", "POST");
	}

	public POSTRequester(Charset charset) {
		this();
		super.charset = charset;
	}

	public String getPayload() {
		return this.payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String doRequest(String address) throws IOException {
		if (this.payload == null) {
			throw new IllegalStateException("Payload is null");
		}

		HttpURLConnection httpUrlConnection = super.prepareConnection(address);

		httpUrlConnection.setDoInput(true);
		httpUrlConnection.setDoOutput(true);
		httpUrlConnection.setRequestMethod(super.requestProperties.get("Request-Method"));

		try (OutputStreamWriter writer = new OutputStreamWriter(httpUrlConnection.getOutputStream(),
				super.charset.name())) {
			writer.write(this.payload);
		}

		String response = super.getResponse(httpUrlConnection);
		return response;
	}

}