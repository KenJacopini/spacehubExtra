/**
 * 
 */
package com.project.spacehub.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.spacehub.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author lordsugar
 *
 */
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class)
	
	@Autowired
	private UserDetailsServiceImpl userDetailsDervice;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private static AuthenticationManager authenticationManager;

	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwtToken = null;
		
		//JWT Token is in the form "Bearer token". Remove Bearer word and get the token
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			try {
				username=jwtTokenUtil.getUsernameFromToken(jwtToken);
			}
			catch(IllegalArgumentException e) {
				log.info("Unable to get JWT token");
			}
			catch(ExpiredJwtException e) {
				log.info("JWT Token has expired");
			}
		
		}
		else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		
		//get token and validate it
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.userDetailsDervice.loadUserByUsername(username);
			
			
			//if token is valid Spring Security creates authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

	public static void authenticate(String username, String password) {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		
		
		
	}
	
}
