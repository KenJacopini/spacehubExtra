/**
 * 
 */
package com.project.spacehub.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;




/**
 * @author lordsugar
 *
 */

@Entity
@Table(name="spacehubUser")
public class SpaceHubUser {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique = true)
	private int id;
	
	@Column(name="email")
	private String email;
	
	
	@Column(name="password")
	private String password;
	
	
	@NotEmpty(message = "First Name cannot be empty")
	@Size(min = 1)
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="enabled")
	private boolean enabled;
	
	
	@NotEmpty(message = "Last Name cannot be empty")
	@Size(min = 1)
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	
	@NotEmpty(message = "Company Name cannot be empty")
	@Size(min = 1)
	@Column(name="company_name")
	private String companyName;
	
	@Transient
	private String passwordConfirm;
	
//	@OneToMany(mappedBy= "userId",  cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	private List <Booking> bookingId;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	@JoinTable(name="spacehubUser_spacehubRole", joinColumns = @JoinColumn(name = "spacehubUser_id", referencedColumnName = "id"),
//					inverseJoinColumns = @JoinColumn(name = "spacehubRole_id", referencedColumnName = "id"))
//	private List<SpaceHubRole> roles;

	
	public SpaceHubUser() {
		
	}
	
	public SpaceHubUser(String email, String password, String firstName, String lastName,
			String phoneNumber) {
		super();
		
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.enabled = false;
	}

	
//	public List<SpaceHubRole> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<SpaceHubRole> roles) {
//		this.roles = roles;
//	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	

//	public List<Booking> getBookingId() {
//		return bookingId;
//	}
//
//	public void setBookingId(List<Booking> bookingId) {
//		this.bookingId = bookingId;
//	}
	
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	

//	public void addRole(SpaceHubRole role) {
//		
//		if(roles == null) {
//			roles = new ArrayList<>();
//		}
//		roles.add(role);
//	}


	@Override
	public String toString() {
//		return "SpaceHubUser [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
//				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", passwordConfirm=" + passwordConfirm
//				+ "]";
		final StringBuilder builder = new StringBuilder();
		builder.append("SpaceHubUser [email=").append(email).append(", firstName=")
			.append(firstName).append(", lastName=").append(lastName).append(" phoneNumber=").append(phoneNumber)
			.append(", companyName=").append(companyName).append(", enabled=").append(enabled).append("]");
		
			return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpaceHubUser other = (SpaceHubUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	
	
	
	

}
