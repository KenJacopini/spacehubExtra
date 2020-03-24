/**
 * 
 */
package com.project.spacehub.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.spacehub.dao.BookingDAO;
import com.project.spacehub.dao.SpaceHubRoleDao;
import com.project.spacehub.dao.SpaceHubUserDao;
import com.project.spacehub.entity.Booking;
import com.project.spacehub.entity.SpaceHubRole;
import com.project.spacehub.entity.SpaceHubUser;
import com.project.spacehub.validate.UserAlreadyExistException;

/**
 * @author gbemisola
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private BookingDAO bookingDAO;
	
	@Autowired
	private SpaceHubUserDao spaceHubUserDao;
	
	@Autowired
	private SpaceHubRoleDao spaceHubRoleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session currentSession() {
		
		return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	@Transactional
	public void bookProduct(Booking book) {
	
		bookingDAO.bookProduct(book);

	}

	

	@Override
	@Transactional
	public void saveSpaceHubUser(SpaceHubUser user) {
				
		//user.setRoles(Arrays.asList(new SpaceHubRole("EMPLOYEE")));
		
		spaceHubUserDao.saveSpaceHubUser(user);
		
	}



	@Override
	public SpaceHubUser getUser(String theEmail) {
		
		return null;
	}



	@Override
	public void deleteUser(SpaceHubUser theUser) {
		
		spaceHubUserDao.deleteSpaceHubUser(theUser);
	}



	@Override
	public void registerNewUserAccount(SpaceHubUser newAccount) {

		if (emailExists(newAccount.getEmail())) {
			
			throw new UserAlreadyExistException("There is an account with this email address: " + newAccount.getEmail());
		}
		
		final SpaceHubUser user = new SpaceHubUser();
		
		user.setFirstName(newAccount.getFirstName());
		user.setLastName(newAccount.getLastName());
		user.setPassword(passwordEncoder.encode(newAccount.getPassword()));
		user.setEmail(newAccount.getEmail());
		user.setCompanyName(newAccount.getCompanyName());
//		user.setRoles(Arrays.asList(spaceHubRoleDao.getRole("ROLE_USER")));
		
		spaceHubUserDao.saveSpaceHubUser(user);
	}



	@Override
	public SpaceHubUser findUserByEmail(String theEmail) {
		
		return spaceHubUserDao.getUser(theEmail);
	}



	@Override
	public Optional<SpaceHubUser> getUserById(long theId) {
		
		Query<SpaceHubUser> theQuery = currentSession()
				.createQuery("from SpaceHubuser order by id", SpaceHubUser.class);
		
		Optional<SpaceHubUser> spaceHubUser = theQuery.uniqueResultOptional();
		
		return spaceHubUser;
		
	}



	@Override
	public List<String> getUsersFromSessionRegistry() {
		
		return sessionRegistry.getAllPrincipals()
				.stream()
				.filter((u) -> ! sessionRegistry.getAllSessions(u, false)
						.isEmpty())
				.map(o ->{
					if (o instanceof SpaceHubUser) {
						return((SpaceHubUser) o).getEmail();
					} else {
						return o.toString();
					}
				})
				.collect(Collectors.toList());
					
	}
	
	private boolean emailExists(String email) {
		
		return spaceHubUserDao.getUser(email) != null;
	}
	
}
