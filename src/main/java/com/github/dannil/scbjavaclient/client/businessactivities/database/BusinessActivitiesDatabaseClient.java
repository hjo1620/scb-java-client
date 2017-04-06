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

package com.github.dannil.scbjavaclient.client.businessactivities.database;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.github.dannil.scbjavaclient.client.AbstractClient;
import com.github.dannil.scbjavaclient.constants.APIConstants;
import com.github.dannil.scbjavaclient.http.URLEndpoint;
import com.github.dannil.scbjavaclient.model.ResponseModel;

/**
 * <p>Client which handles business activities database data fetching.</p>
 *
 * @since 0.3.0
 */
public class BusinessActivitiesDatabaseClient extends AbstractClient {

    /**
     * <p>Default constructor.</p>
     */
    public BusinessActivitiesDatabaseClient() {
        super();
    }

    /**
     * <p>Overloaded constructor.</p>
     *
     * @param locale
     *            the <code>Locale</code> for this client
     */
    public BusinessActivitiesDatabaseClient(Locale locale) {
        super(locale);
    }

    public List<ResponseModel> getEnterprisesAndEmployeesSNI2002() {
        return getEnterprisesAndEmployeesSNI2002(null, null, null);
    }

    public List<ResponseModel> getEnterprisesAndEmployeesSNI2002(Collection<String> industrialClassifications,
            Collection<String> sizeClasses, Collection<Integer> years) {
        return generate(industrialClassifications, sizeClasses, years, "FDBR");
    }

    public List<ResponseModel> getEnterprisesAndEmployeesSNI2007() {
        return getEnterprisesAndEmployeesSNI2007(null, null, null);
    }

    public List<ResponseModel> getEnterprisesAndEmployeesSNI2007(Collection<String> industrialClassifications,
            Collection<String> sizeClasses, Collection<Integer> years) {
        return generate(industrialClassifications, sizeClasses, years, "FDBR07");
    }

    /**
     * <p>Common generator method for the methods in this class.</p>
     *
     * @param industrialClassifications
     *            the industrial classifications
     * @param sizeClasses
     *            the size classes
     * @param years
     *            the years
     * @param table
     *            the table
     * @return a <code>List</code> of
     *         {@link com.github.dannil.scbjavaclient.model.ResponseModel ResponseModel}
     */
    private List<ResponseModel> generate(Collection<String> industrialClassifications, Collection<String> sizeClasses,
            Collection<Integer> years, String table) {
        Map<String, Collection<?>> mappings = new HashMap<>();
        mappings.put("SNI2007", industrialClassifications);
        mappings.put("Storleksklass", sizeClasses);
        mappings.put(APIConstants.TIME_CODE, years);

        return getResponseModels(table, mappings);
    }

    @Override
    public URLEndpoint getUrl() {
        return getRootUrl().append("NV/NV0101/");
    }

}
