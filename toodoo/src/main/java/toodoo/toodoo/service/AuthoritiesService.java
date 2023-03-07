package toodoo.toodoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toodoo.toodoo.domain.Authorities;
import toodoo.toodoo.repository.AuthoritiesRepo;

@Service
public class AuthoritiesService {
	@Autowired
	private AuthoritiesRepo authoritiesrepo;
	
	public Authorities saveAuthorities(Authorities author) {
		return authoritiesrepo.save(author);
	}
}
