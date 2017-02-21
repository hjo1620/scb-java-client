/*
 * Copyright 2016 Daniel Nilsson
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

package com.github.dannil.scbjavaclient.utility;

import java.util.Locale;

import com.github.dannil.scbjavaclient.constants.APIConstants;

/**
 * <p>Utility class which handles URL operations.</p>
 *
 * @since 0.0.2
 */
public final class URLUtility {

    /**
     * <p>Private constructor to prevent instantiation.</p>
     */
    private URLUtility() {

    }

    /**
     * <p>Returns the root URL for the API.</p>
     *
     * @return the {@link com.github.dannil.scbjavaclient.constants.APIConstants#ROOT_URL
     *         ROOT_URL}
     */
    public static String getRootUrl() {
        return APIConstants.ROOT_URL;
    }

    /**
     * <p>Returns the root URL for the API for a specific <code>Locale</code>.</p>
     *
     * @param locale
     *            the <code>Locale</code>
     * @return the {@link com.github.dannil.scbjavaclient.constants.APIConstants#ROOT_URL
     *         ROOT_URL} with a converted language tag segment matching the specified
     *         <code>Locale</code>
     */
    public static String getRootUrl(Locale locale) {
        return changeLanguageForUrl(APIConstants.ROOT_URL, locale);
    }

    /**
     * <p>Generates a new URL to the API using the fallback <code>Locale</code>
     * {@link com.github.dannil.scbjavaclient.constants.APIConstants#FALLBACK_LOCALE
     * FALLBACK_LOCALE}.</p>
     *
     * <p>See {@link #changeLanguageForUrl(String, String)} for implementation
     * details.</p>
     *
     * @param url
     *            the URL to edit
     * @return the modified URL
     */
    public static String changeLanguageForUrl(String url) {
        return changeLanguageForUrl(url, APIConstants.FALLBACK_LOCALE);
    }

    /**
     * <p>Generates a new URL to the API using the specified <code>Locale</code>.</p>
     *
     * <p>See {@link #changeLanguageForUrl(String, String)} for implementation
     * details.</p>
     *
     * @param url
     *            the URL to edit
     * @param locale
     *            the <code>Locale</code> to use
     * @return the modified URL
     */
    public static String changeLanguageForUrl(String url, Locale locale) {
        return changeLanguageForUrl(url, locale.getLanguage());
    }

    /**
     * <p>Generates a new URL to the API by replacing the current language tag in the URL
     * with the specified language tag.</p>
     *
     * <p>This method performs these steps to figure out what needs to be replaced:</p>
     *
     * <ol> <li>Specifies the <b>start segment</b> as the segment preceding the
     * <b>language tag segment</b> in the URL.</li>
     *
     * <li>Finds the length of the <b>language tag segment</b> by finding the next forward
     * slash following the <b>start segment</b>, as this indicates that the segment has
     * ended.</li>
     *
     * <li>Replaces the content between the start and end of the segment (forward slashes
     * excluded) with the new language tag.</li> </ol>
     *
     * <p>Example: URL input of <b>https://api.scb.se/OV0104/v1/doris/sv/ssd/</b> and
     * language input of <b>en</b> is converted to
     * <b>https://api.scb.se/OV0104/v1/doris/en/ssd/</b>.</p>
     *
     * <p>Due to speed efficiency, this method does not perform any validity check on the
     * specified URL. Calling this method without a valid URL for the API may (and
     * probably will) result in undefined behavior.</p>
     *
     * @param url
     *            the URL to edit
     * @param language
     *            the language to use
     * @return the modified URL
     */
    public static String changeLanguageForUrl(String url, String language) {
        // Specify the starting point. For this implementation, the starting
        // point is the segment preceding the language tag segment in the URL
        String startSegment = "doris";

        // Find the index where the language tag starts
        int start = url.indexOf(startSegment) + startSegment.length() + 1;
        // Find the index where the language tag ends
        int end = start + url.substring(start).indexOf('/');

        // Replace the contents between the start and end index with our new
        // language tag
        StringBuilder builder = new StringBuilder(url);
        builder.replace(start, end, language);

        return builder.toString();
    }

}
