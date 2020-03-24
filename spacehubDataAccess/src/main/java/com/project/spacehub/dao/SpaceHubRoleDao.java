/**
 * 
 */
package com.project.spacehub.dao;

import java.util.List;

import com.project.spacehub.entity.SpaceHubRole;


/**
 * @author lordsugar
 *
 */
public interface SpaceHubRoleDao {
	
	public SpaceHubRole getRole(String theRole);
	
	public List <SpaceHubRole> getRoles();
	

}
