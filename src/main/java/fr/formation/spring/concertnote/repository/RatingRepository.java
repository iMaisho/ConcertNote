package fr.formation.spring.concertnote.repository;

import fr.formation.spring.concertnote.model.entity.Concert;
import fr.formation.spring.concertnote.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllRatingByConcertId(Long concert_id);
}
