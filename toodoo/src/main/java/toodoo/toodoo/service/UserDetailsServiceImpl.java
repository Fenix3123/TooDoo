package toodoo.toodoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import toodoo.toodoo.domain.User;
import toodoo.toodoo.repository.userRepo;
import toodoo.toodoo.security.CustomSecurityUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private userRepo userrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userrepo.findByUsername(username);
		
		if(user == null)
			throw new UsernameNotFoundException("Username or password not correct");
		return new CustomSecurityUser(user);
	}

}
