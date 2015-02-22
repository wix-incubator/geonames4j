package org.geonames;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.http.HttpRequestFactory;

public class GeoNamesClientTest {
	private static String USERNAME = "geoservices"; // TODO: use real value
	
	private static final HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
	private static final GeoNamesClient client = new GeoNamesClient(requestFactory, null, null, null);

//	@Test
	public void testIsrael() throws Exception {
		final TimezoneResponse response = client.getTimezone(USERNAME, 32.0863375808, 34.7767803136); // Arlozorov 58, Tel Aviv, Israel
		assertEquals("Asia/Jerusalem", response.timezoneId);
		assertEquals("IL", response.countryCode);
	}
	
//	@Test
	public void testEurope() throws Exception {
		final TimezoneResponse response = client.getTimezone(USERNAME, 47.01, 10.2); // Vienna, Austria
		assertEquals("Europe/Vienna", response.timezoneId);
		assertEquals("AT", response.countryCode);
	}
	
//	@Test
	public void testError() throws Exception {
		try {
			client.getTimezone(USERNAME, Double.NaN, Double.NaN);
			fail("Expected exception");
		} catch (GeoNamesException e) {
			// Expected exception
		}
	}
}
