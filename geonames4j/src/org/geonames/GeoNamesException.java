package org.geonames;

public class GeoNamesException extends Exception {
	private static final long serialVersionUID = 1L;
	private final Integer code;
	
	public GeoNamesException(Status status) {
		super(status.message);
		this.code = status.value;
	}
	
	public GeoNamesException(Status status, Throwable cause) {
		super(status.message, cause);
		this.code = status.value;
	}
	
	public Integer getCode() {
		return code;
	}
}
