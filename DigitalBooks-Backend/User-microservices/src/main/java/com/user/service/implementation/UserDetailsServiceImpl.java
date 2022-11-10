package com.user.service.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.user.entity.User;
import com.user.repository.IUserRepo;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	IUserRepo userRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Name is not Found!!! Check again before Signing"));
		return UserDetailsImpl.build(user);
	}

}
