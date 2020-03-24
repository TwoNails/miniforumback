package co.simplon.miniforum.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.miniforum.models.ForumUser;

public interface ForumUserRepository extends JpaRepository<ForumUser, Integer> {
	
	public Optional<ForumUser> findByUserName(String username);
	
}
