/**
 * 
 */
package com.project.spacehub.entity;

import javax.validation.constraints.Email;

/**
 * @author lordsugar
 *
 */
public class LoginDTO{
	
	@Email(message = "Email must be valid")
	private String email;
	
	private String password;
	
	private Boolean rememberMe;

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

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", rememberMe=" + rememberMe + "]";
	}
	
	
	
	

}
