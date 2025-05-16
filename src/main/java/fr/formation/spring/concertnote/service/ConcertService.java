package fr.formation.spring.concertnote.service;

import fr.formation.spring.concertnote.model.dto.ConcertDto;
import fr.formation.spring.concertnote.model.entity.Concert;
import fr.formation.spring.concertnote.repository.BandRepository;
import fr.formation.spring.concertnote.repository.ConcertRepository;
import fr.formation.spring.concertnote.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final BandRepository bandRepository;
    private final VenueRepository venueRepository;

    public ConcertService(ConcertRepository concertRepository,
                          BandRepository bandRepository,
                          VenueRepository venueRepository)
    {
        this.concertRepository = concertRepository;
        this.bandRepository = bandRepository;
        this.venueRepository = venueRepository;
    }

    public List<Concert> getAllConcerts() {
        return concertRepository.findAllByOrderByConcertDateDesc();
    }

    public void addConcert(ConcertDto dto) {
        Concert concert = new Concert();
        concert.setBand(bandRepository.getById(dto.getBand()));
        concert.setVenue(venueRepository.getById(dto.getVenue()));
        concert.setDescription(dto.getDescription());
        concert.setConcertDate(dto.getConcertDate());
        concertRepository.save(concert);
    }
}
