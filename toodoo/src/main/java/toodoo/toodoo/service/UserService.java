package toodoo.toodoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toodoo.toodoo.domain.User;
import toodoo.toodoo.repository.userRepo;

@Service
public class UserService {
	@Autowired
	private userRepo userrepo;
	
	public User saveUser(User user) {
		return userrepo.save(user);
	}

}
