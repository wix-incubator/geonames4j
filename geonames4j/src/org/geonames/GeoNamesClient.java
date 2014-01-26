package org.geonames;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;

public class GeoNamesClient {
	private static final String GEONAMES_API_URL = "http://api.geonames.org/";
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private final HttpRequestFactory requestFactory;
	private final Integer connectTimeout;
	private final Integer readTimeout;
	private final String username;
	
	public GeoNamesClient(HttpRequestFactory requestFactory, Integer connectTimeout, Integer readTimeout,
			String username) {
		this.requestFactory = requestFactory;
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
		this.username = username;
	}
	
	public GeoNamesClient(HttpRequestFactory requestFactory, String username) {
		this(requestFactory, null, null, username);
	}
	
	public TimezoneResponse getTimezone(double lat, double lng) throws GeoNamesException, IOException {
		final TimezoneResponse response = getTimezoneImpl(lat, lng);
		if (response.status != null) {
			throw new GeoNamesException(response.status);
		}
		return response;
	}
	
	private TimezoneResponse getTimezoneImpl(double lat, double lng) throws IOException {
        final String url = GEONAMES_API_URL + "timezoneJSON"
        		+ "?lat=" + lat
        		+ "&lng=" + lng
        		+ "&username=" + URLEncoder.encode(username, "UTF-8");
        
    	final HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(url));
        if (connectTimeout != null) {
        	request.setConnectTimeout(connectTimeout.intValue());
        }
        if (readTimeout != null) {
        	request.setReadTimeout(readTimeout.intValue());
        }

        final HttpResponse response = request.execute();
        try {
	        final InputStream is = response.getContent();
	        try {
	            return mapper.readValue(is, TimezoneResponse.class);
	        } finally {
	            is.close();
	        }
        } finally {
        	response.ignore();
        }
	}
}
