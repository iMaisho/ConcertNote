package fr.formation.spring.concertnote.repository;

import fr.formation.spring.concertnote.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUsersByEmail(String email);
}