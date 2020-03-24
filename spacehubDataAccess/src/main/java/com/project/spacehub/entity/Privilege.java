/**
 * 
 */
package com.project.spacehub.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author lordsugar
 *
 */
@Entity
@Table(name="privileges")
public class Privilege {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="privileges_name")
	private String name;
	
	@ManyToMany(mappedBy = "privileges")
	private List<SpaceHubRole> roles;
	
	
	public Privilege() {
		super();
	}


	public Privilege(String name) {
		super();
		this.name = name;
	}

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<SpaceHubRole> getRoles() {
		return roles;
	}


	public void setRoles(List<SpaceHubRole> roles) {
		this.roles = roles;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Privilege other = (Privilege) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("Privilege [id=").append(id).append("]");
		
		return builder.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
