package com.metacube.sageclarity.predictable.service.securityhelpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metacube.sageclarity.predictable.entity.User;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.service.UserService;

@Service("customeUserDetailService")
public class CustomeUserDetailService implements UserDetailsService{
	private static final Logger logger = LoggerFactory.getLogger(CustomeUserDetailService.class);
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user;
		try {
			user = userService.getByUserName(userName);
			if(user == null) {
				logger.error("User with %s doesn't exist!", userName);
				throw new UsernameNotFoundException(String.format("User with %s doesn't exist!", userName));
			}
		} catch (ApplicationLevelException e) {
			logger.error("User doesn't exist with user name :-" +userName, e);
			throw new UsernameNotFoundException(String.format(e.getMessage(), userName));
		}
	  
	    return new UserAdapter(user);
	}
}
