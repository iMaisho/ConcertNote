package fr.formation.spring.concertnote.repository;

import fr.formation.spring.concertnote.model.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
}
