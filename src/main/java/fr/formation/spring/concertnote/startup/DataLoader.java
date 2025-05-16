package fr.formation.spring.concertnote.startup;

import fr.formation.spring.concertnote.model.entity.*;
import fr.formation.spring.concertnote.repository.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BandRepository bandRepository;
    private final VenueRepository venueRepository;
    private final ConcertRepository concertRepository;
    private final RatingRepository ratingRepository;

    public DataLoader(UserRepository userRepository,
                      BandRepository bandRepository,
                      VenueRepository venueRepository,
                      ConcertRepository concertRepository,
                      RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.bandRepository = bandRepository;
        this.venueRepository = venueRepository;
        this.concertRepository = concertRepository;
        this.ratingRepository = ratingRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing Data");

        User user = new User();
        user.setUsername("admin");
        user.setEmail("a@a.com");
        user.setPasswordHash(BCrypt.hashpw("admin", BCrypt.gensalt()));
        userRepository.save(user);

        User user2 = new User();
        user2.setUsername("joe");
        user2.setEmail("joe@a.com");
        user2.setPasswordHash(BCrypt.hashpw("admin", BCrypt.gensalt()));
        userRepository.save(user2);

        System.out.println("Users created");

        Band band = new Band();
        band.setName("Radiohead");
        band.setGenre("Awesome");
        bandRepository.save(band);

        System.out.println("Band created");

        Venue venue = new Venue();
        venue.setName("Astrolabe");
        venue.setCity("Orléans");
        venueRepository.save(venue);

        System.out.println("Venue created");

        Concert concert = new Concert();
        concert.setConcertDate(LocalDate.now());
        concert.setBand(band);
        concert.setVenue(venue);
        concert.setDescription("Le concert du siècle");
        concertRepository.save(concert);

        System.out.println("Concert created");

        Rating rating = new Rating();
        rating.setConcert(concert);
        rating.setUser(user);
        rating.setScore(5);
        rating.setRatingDate(LocalDate.now());
        rating.setDescription("Amazing concert");
        ratingRepository.save(rating);

        Rating rating2 = new Rating();
        rating2.setConcert(concert);
        rating2.setUser(user2);
        rating2.setScore(3);
        rating2.setRatingDate(LocalDate.now());
        rating2.setDescription("Pretty good concert");
        ratingRepository.save(rating2);

    }
}
