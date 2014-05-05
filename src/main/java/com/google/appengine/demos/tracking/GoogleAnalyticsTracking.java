/**
 * Copyright 2014 Google Inc. All Rights Reserved.
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

// [START tracking_code]
package com.google.appengine.demos.tracking;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GoogleAnalyticsTracking {

  // Set this to the specific Google Analytics Tracking Id for your application.
  private static final String GA_TRACKING_ID = "UA-49801701-2";
  private static final URL GA_URL_ENDPOINT = getGoogleAnalyticsEndpoint();
  private static final HTTPHeader CONTENT_TYPE_HEADER =
      new HTTPHeader("Content-Type", "application/x-www-form-urlencoded");

  private static URL getGoogleAnalyticsEndpoint() {
    try {
      return new URL("http", "www.google-analytics.com", "/collect");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  private static URLFetchService urlFetchService = URLFetchServiceFactory.getURLFetchService();

  // Used to override the existing factory with perhaps a mock one for testing.
  public static void setUrlFetchService(URLFetchService newUrlFetchService) {
    urlFetchService = newUrlFetchService;
  }
  /**
   * Posts an Event Tracking message to Google Analytics.
   *
   * @param category the required event category
   * @param action the required event action
   * @param label the optional event label
   * @param value the optional value
   * @return true if the call succeeded, otherwise false
   * @exception IOException if the URL could not be posted to
   */
  public static boolean trackEventToGoogleAnalytics(
      String category, String action, String label, String value) throws IOException {
    Map<String, String> map = new HashMap<>();
    map.put("v", "1");                      // Version.
    map.put("tid", GA_TRACKING_ID);         // Tracking ID / Web property / Property ID
    map.put("cid", "555");                  // Anonymous Client ID.
    map.put("t", "event");                  // Event hit type.
    map.put("ec", encode(category, true));
    map.put("ea", encode(action, true));
    map.put("el", encode(label, false));
    map.put("ev", encode(value, false));

    String postData = getPostData(map);

    HTTPRequest request = new HTTPRequest(GA_URL_ENDPOINT, HTTPMethod.POST);
    request.addHeader(CONTENT_TYPE_HEADER);
    request.setPayload(postData.getBytes());

    HTTPResponse httpResponse = urlFetchService.fetch(request);
    // Return True if the call was successful.
    return httpResponse.getResponseCode() == HttpURLConnection.HTTP_OK;
  }

  private static String getPostData(Map<String, String> map) {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      sb.append(entry.getKey());
      sb.append('=');
      sb.append(entry.getValue());
      sb.append('&');
    }
    if (sb.length() > 0) {
      sb.setLength(sb.length() - 1); // Remove the trailing &.
    }
    return sb.toString();
  }

  private static String encode(String value, boolean required)
      throws UnsupportedEncodingException {
    if (value == null) {
      if (required) {
        throw new IllegalArgumentException("Null value");
      }
      return "";
    }
    return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
  }
}
// [END tracking_code]
