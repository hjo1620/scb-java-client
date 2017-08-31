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

package com.github.dannil.scbjavaclient.client.educationandresearch;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import com.github.dannil.scbjavaclient.client.educationandresearch.activityaftertraining.EducationAndResearchActivityAfterTrainingClient;
import com.github.dannil.scbjavaclient.client.educationandresearch.communityinnovationsurvey.EducationAndResearchCommunityInnovationSurveyClient;
import com.github.dannil.scbjavaclient.http.URLEndpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EducationAndResearchClientTest {

    @Test
    public void createWithLocaleConstructor() {
        Locale locale = new Locale("sv", "SE");
        EducationAndResearchClient client = new EducationAndResearchClient(locale);

        assertEquals(locale, client.getLocale());
    }

    @Test
    public void activityAfterTraining() {
        EducationAndResearchClient client = new EducationAndResearchClient();

        assertEquals(client.activityAfterTraining().getClass(), EducationAndResearchActivityAfterTrainingClient.class);
    }

    @Test
    public void communityInnovationSurvey() {
        EducationAndResearchClient client = new EducationAndResearchClient();

        assertEquals(client.communityInnovationSurvey().getClass(),
                EducationAndResearchCommunityInnovationSurveyClient.class);
    }

    @Test
    public void getUrl() {
        // Check with a locale that isn't the fallback locale; results in a more specific
        // test with harder constraints
        Locale locale = new Locale("en", "US");
        EducationAndResearchClient client = new EducationAndResearchClient(locale);

        assertEquals(URLEndpoint.getRootUrl(locale).append("UF/"), client.getUrl());
    }

}