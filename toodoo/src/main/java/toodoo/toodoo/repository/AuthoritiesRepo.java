package toodoo.toodoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import toodoo.toodoo.domain.Authorities;

public interface AuthoritiesRepo extends JpaRepository<Authorities, Long>{

}
