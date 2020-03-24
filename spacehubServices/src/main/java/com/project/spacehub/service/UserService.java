/**
 * 
 */
package com.project.spacehub.service;

import java.util.List;
import java.util.Optional;

import com.project.spacehub.entity.Booking;
import com.project.spacehub.entity.SpaceHubUser;
import com.project.spacehub.validate.UserAlreadyExistException;


/**
 * @author gbemisola
 *
 */
public interface UserService {

	
	public void bookProduct(Booking book);
	
	public void saveSpaceHubUser(SpaceHubUser theUser);

	public SpaceHubUser getUser(String theEmail);
	
	public void deleteUser(SpaceHubUser theUser);
	
	public void registerNewUserAccount(SpaceHubUser newAccount) throws UserAlreadyExistException;
	
	public SpaceHubUser findUserByEmail(String theEmail);
	
	Optional<SpaceHubUser> getUserById(long theId);
	
	List<String> getUsersFromSessionRegistry();
}
