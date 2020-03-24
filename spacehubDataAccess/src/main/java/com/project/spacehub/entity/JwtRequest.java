/**
 * 
 */
package com.project.spacehub.entity;

import java.io.Serializable;

/**
 * @author lordsugar
 *
 */
public class JwtRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3985137689943301170L;

	private String email;
	private String password;
	
	
	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public JwtRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
