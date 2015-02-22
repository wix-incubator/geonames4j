package org.geonames;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.wix.restaurants.json.Json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GeoNamesClient {
	private static final String GEONAMES_API_URL = "http://api.geonames.org/";

	private final HttpRequestFactory requestFactory;
	private final Integer connectTimeout;
	private final Integer readTimeout;
    private final Integer numberOfRetries;
	private final String username;
	
	public GeoNamesClient(HttpRequestFactory requestFactory, Integer connectTimeout, Integer readTimeout, Integer numberOfRetries,
			String username) {
		this.requestFactory = requestFactory;
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
        this.numberOfRetries = numberOfRetries;
		this.username = username;
	}
	
	public TimezoneResponse getTimezone(double lat, double lng) throws GeoNamesException, IOException {
		final TimezoneResponse response = getTimezoneImpl(lat, lng);
		if (response.status != null) {
			throw new GeoNamesException(response.status);
		}
		return response;
	}

    private static String encode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
	
	private TimezoneResponse getTimezoneImpl(double lat, double lng) throws IOException {
        final String url = GEONAMES_API_URL + "timezoneJSON"
        		+ "?lat=" + encode(Double.toString(lat))
        		+ "&lng=" + encode(Double.toString(lng))
        		+ "&username=" + encode(username);
        
    	final HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(url));
        if (connectTimeout != null) {
        	request.setConnectTimeout(connectTimeout);
        }
        if (readTimeout != null) {
        	request.setReadTimeout(readTimeout);
        }
        if (numberOfRetries != null) {
            request.setNumberOfRetries(numberOfRetries);
        }

        final HttpResponse response = request.execute();
        try {
            return Json.parse(response.parseAsString(), TimezoneResponse.class);
        } finally {
        	response.ignore();
        }
	}
}
