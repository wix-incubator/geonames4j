package org.geonames;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * GeoNames API response status.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** Default constructor for JSON deserialization. */
	public Status() {}
	
	public String message;
	public Integer value;
}
