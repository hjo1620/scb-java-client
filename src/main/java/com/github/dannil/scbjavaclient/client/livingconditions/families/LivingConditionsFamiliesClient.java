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

package com.github.dannil.scbjavaclient.client.livingconditions.families;

import java.util.Locale;

import com.github.dannil.scbjavaclient.client.AbstractContainerClient;
import com.github.dannil.scbjavaclient.client.livingconditions.families.adoptions.LivingConditionsFamiliesAdoptionsClient;
import com.github.dannil.scbjavaclient.client.livingconditions.families.housing.LivingConditionsFamiliesHousingClient;
import com.github.dannil.scbjavaclient.client.livingconditions.families.legalguardians.LivingConditionsFamiliesLegalGuardiansClient;
import com.github.dannil.scbjavaclient.client.livingconditions.families.siblings.LivingConditionsFamiliesSiblingsClient;

/**
 * <p>Client which handles living conditions families data fetching.</p>
 *
 * @since 0.2.0
 */
public class LivingConditionsFamiliesClient extends AbstractContainerClient {

    private LivingConditionsFamiliesAdoptionsClient livingConditionsFamiliesAdoptionsClient;

    private LivingConditionsFamiliesHousingClient livingConditionsFamiliesHousingClient;

    private LivingConditionsFamiliesLegalGuardiansClient livingConditionsFamiliesLegalGuardiansClient;

    private LivingConditionsFamiliesSiblingsClient livingConditionsFamiliesSiblingsClient;

    /**
     * <p>Default constructor. Initializes values and creates sub-clients.</p>
     */
    public LivingConditionsFamiliesClient() {
        super();

        this.livingConditionsFamiliesAdoptionsClient = new LivingConditionsFamiliesAdoptionsClient();
        addClient(this.livingConditionsFamiliesAdoptionsClient);

        this.livingConditionsFamiliesHousingClient = new LivingConditionsFamiliesHousingClient();
        addClient(this.livingConditionsFamiliesHousingClient);

        this.livingConditionsFamiliesLegalGuardiansClient = new LivingConditionsFamiliesLegalGuardiansClient();
        addClient(this.livingConditionsFamiliesLegalGuardiansClient);

        this.livingConditionsFamiliesSiblingsClient = new LivingConditionsFamiliesSiblingsClient();
        addClient(this.livingConditionsFamiliesSiblingsClient);
    }

    /**
     * <p>Overloaded constructor.</p>
     *
     * @param locale
     *            the <code>Locale</code> for this client
     */
    public LivingConditionsFamiliesClient(Locale locale) {
        this();

        setLocale(locale);
    }

    /**
     * <p>Retrieve the client for interacting with living conditions families adoptions
     * data.</p>
     *
     * @return a client for living conditions families adoptions data
     */
    public LivingConditionsFamiliesAdoptionsClient adoptions() {
        return this.livingConditionsFamiliesAdoptionsClient;
    }

    /**
     * <p>Retrieve the client for interacting with living conditions families housing
     * data.</p>
     *
     * @return a client for living conditions families housing data
     */
    public LivingConditionsFamiliesHousingClient housing() {
        return this.livingConditionsFamiliesHousingClient;
    }

    /**
     * <p>Retrieve the client for interacting with living conditions families legal
     * guardians data.</p>
     *
     * @return a client for living conditions families legal guardians data
     */
    public LivingConditionsFamiliesLegalGuardiansClient legalGuardians() {
        return this.livingConditionsFamiliesLegalGuardiansClient;
    }

    /**
     * <p>Retrieve the client for interacting with living conditions families siblings
     * data.</p>
     *
     * @return a client for living conditions families siblings data
     */
    public LivingConditionsFamiliesSiblingsClient siblings() {
        return this.livingConditionsFamiliesSiblingsClient;
    }

    @Override
    public String getUrl() {
        return getRootUrl() + "LE/LE0102/";
    }

}