package toodoo.toodoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import toodoo.toodoo.domain.User;

public interface userRepo extends JpaRepository<User, Long>{
	
	@Query("Select u from User u left join fetch u.authorities where u.username = :username")
	public User findByUsername(String username);
	
	
}
