/**
 * 
 */
package com.project.spacehub.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author lordsugar
 *
 */

@Entity
@Table(name="spacehubRole")
public class SpaceHubRole {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="role_name")
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	private List<SpaceHubUser> users;
	
	@ManyToMany(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="spacehubRole_privileges", joinColumns = @JoinColumn(name = "spacehubRole_id", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name = "privileges_id", referencedColumnName = "id"))
	private List<Privilege> privileges;

	
	
	public SpaceHubRole(String roleName) {
		super();
		this.roleName = roleName;
	}

	public int getid() {
		return id;
		
	}

	public void setRoleId(int roleId) {
		this.id = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public List<SpaceHubUser> getUsers() {
		return users;
	}

	public void setUsers(List<SpaceHubUser> users) {
		this.users = users;
	}

	public List<Privilege> getPriveleges() {
		return privileges;
	}

	public void setPriveleges(List<Privilege> privileges) {
		this.privileges = privileges;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
		SpaceHubRole other = (SpaceHubRole) obj;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("SpaceHubRole [roleName=").append(roleName).append("]").append("[id=").append(id).append("]");
		return builder.toString();
	}

}
