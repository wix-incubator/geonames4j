package org.geonames;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimezoneResponse extends Response {
	private static final long serialVersionUID = 1L;
	
	/** Default constructor for JSON deserialization. */
	public TimezoneResponse() {}
	
	public Double lat;
	public Double lng;
	public String time;
	public String sunrise;
	public String sunset;
	public Integer rawOffset;
	public Integer dstOffset;
	public Integer gmtOffset;
	public String timezoneId;
	public String countryName;
	public String countryCode;
}
