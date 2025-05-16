package fr.formation.spring.concertnote.repository;

import fr.formation.spring.concertnote.model.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {

    List<Concert> findAllByOrderByConcertDateDesc();
}
