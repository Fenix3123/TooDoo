package toodoo.toodoo.service;

import java.util.Optional;

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
	
	public User findById(Long id) {
		Optional<User> userOpt = userrepo.findById(id);
		return userOpt.orElse(new User());
	}

}
