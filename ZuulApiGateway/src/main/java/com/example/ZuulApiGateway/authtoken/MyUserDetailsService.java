package com.example.ZuulApiGateway.authtoken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.ZuulApiGateway.entity.UserInfo;


@Component
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String accessToken) throws UsernameNotFoundException {
		SpringSession session = null;
		session = userDao.getSessionBySessionId(accessToken);
		if (session == null) {
			MyUserDetails details=new MyUserDetails();
			details.setInvaliDSession(false);
			return details;
		}
		// validate session
		if (new Date().getTime() > session.getExpiryTime()) {
			MyUserDetails details=new MyUserDetails();
			details.setExpireSession(false);
			return details;
		}

		// get user form cache

		UserInfo user = userRepository.findUserByEmailId(session.getPrincipalName());
		if (user == null) {
			return null;
//			throw new UsernameNotFoundException(session.getPrincipalName());
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getUserType()));
		

		return new MyUserDetails(new UserDto(user.getEmailId(), user.getPassword()), authorities);
	}
}