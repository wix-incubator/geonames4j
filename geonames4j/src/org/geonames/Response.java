package org.geonames;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Base class for all GeoNames API responses.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable {
	private static final long serialVersionUID = 1L;

	/** Default constructor for JSON deserialization. */
	public Response() {}
	
	public Status status;
}
