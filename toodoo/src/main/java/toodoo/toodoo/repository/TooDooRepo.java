package toodoo.toodoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toodoo.toodoo.domain.TooDoo;

public interface TooDooRepo extends JpaRepository<TooDoo, Long>{

}
