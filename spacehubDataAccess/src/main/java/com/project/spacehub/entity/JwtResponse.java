/**
 * 
 */
package com.project.spacehub.entity;

import java.io.Serializable;

/**
 * @author lordsugar
 *
 */
public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2807919261301709466L;

	private final String jwttoken;

	
	public JwtResponse(String jwttoken) {
		super();
		this.jwttoken = jwttoken;
	}
	
	public String jwttoken() {
		
		return this.jwttoken;
	}
}
