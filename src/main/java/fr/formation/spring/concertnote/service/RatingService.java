package fr.formation.spring.concertnote.service;

import fr.formation.spring.concertnote.model.dto.RatingDto;
import fr.formation.spring.concertnote.model.entity.Rating;
import fr.formation.spring.concertnote.repository.ConcertRepository;
import fr.formation.spring.concertnote.repository.RatingRepository;
import fr.formation.spring.concertnote.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final ConcertRepository concertRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository,
                         ConcertRepository concertRepository,
                         UserRepository userRepository)
    {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.concertRepository = concertRepository;
    }

    public List<Rating> getAllRatings(Long concert_id) {
        return ratingRepository.findAllRatingByConcertId(concert_id);
    }

    public void addRating(RatingDto dto) {
        Rating rating = new Rating();
        rating.setUser(userRepository.getById(dto.getUserId()));
        rating.setConcert(concertRepository.getById(dto.getConcertId()));
        rating.setScore(dto.getScore());
        rating.setDescription(dto.getDescription());
        ratingRepository.save(rating);
    }

    public double getScoreAverage(Long concert_id) {
        List<Rating> ratings = ratingRepository.findAllRatingByConcertId(concert_id);
        double sum = 0.0;
        for (Rating rating : ratings) {
            sum += rating.getScore();
        }
        return sum / ratings.size();
    }
}
