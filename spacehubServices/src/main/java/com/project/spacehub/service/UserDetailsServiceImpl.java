/**
 * 
 */
package com.project.spacehub.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.project.spacehub.dao.SpaceHubUserDao;
import com.project.spacehub.entity.SpaceHubRole;
import com.project.spacehub.entity.SpaceHubUser;

/**
 * @author lordsugar
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private SpaceHubUserDao spaceHubUserDao;
	
	@Autowired
	private PasswordEncoder passwordEndoder;
	
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		SpaceHubUser user = spaceHubUserDao.getUser(email);
		
		 if(user == null){
			 
			   throw new UsernameNotFoundException("username was not found in the database");
			  }
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}
	
	

}
