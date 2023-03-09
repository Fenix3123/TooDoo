package toodoo.toodoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toodoo.toodoo.domain.TooDoo;
import toodoo.toodoo.repository.TooDooRepo;

@Service
public class TooDooService {
	@Autowired
	private TooDooRepo toodoorepo;
	
	public TooDoo saveTooDoo(TooDoo toodoo) {
		return toodoorepo.save(toodoo);
	}
	
	public TooDoo getById(Long id) {
		return toodoorepo.getById(id);
	}
	
	public void delete(TooDoo toodoo) {
		toodoorepo.delete(toodoo);
	}

}
