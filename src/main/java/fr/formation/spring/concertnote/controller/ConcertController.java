package fr.formation.spring.concertnote.controller;

import fr.formation.spring.concertnote.model.dto.ConcertDto;
import fr.formation.spring.concertnote.model.entity.Concert;
import fr.formation.spring.concertnote.model.entity.Rating;
import fr.formation.spring.concertnote.repository.BandRepository;
import fr.formation.spring.concertnote.repository.ConcertRepository;
import fr.formation.spring.concertnote.repository.RatingRepository;
import fr.formation.spring.concertnote.repository.VenueRepository;
import fr.formation.spring.concertnote.service.ConcertService;
import fr.formation.spring.concertnote.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ConcertController {

    private final ConcertService concertService;
    private final BandRepository bandRepository;
    private final VenueRepository venueRepository;
    private final ConcertRepository concertRepository;
    private final RatingRepository ratingRepository;
    private final RatingService ratingService;

    public ConcertController(ConcertService concertService,
                             BandRepository bandRepository,
                             VenueRepository venueRepository,
                             ConcertRepository  concertRepository,
                             RatingRepository ratingRepository,
                             RatingService ratingService)
    {
        this.concertService = concertService;
        this.bandRepository = bandRepository;
        this.venueRepository = venueRepository;
        this.concertRepository = concertRepository;
        this.ratingRepository = ratingRepository;
        this.ratingService = ratingService;
    }

    @GetMapping("/concerts")
    public String showConcerts(Model model) {
        model.addAttribute("concerts", concertService.getAllConcerts());
        return "concerts";
    }

    @GetMapping("/concerts/add")
    public String showNewConcertForm(Model model) {
        model.addAttribute("concert", new ConcertDto());
        model.addAttribute("bands", bandRepository.findAll());
        model.addAttribute("venues", venueRepository.findAll());
        return "add-concert";
    }

    @PostMapping("/concerts/add")
    public String processNewConcert(
            @Valid
            @ModelAttribute("concert") ConcertDto dto,
            BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "add-concert";

        }
        concertService.addConcert(dto);
        return "redirect:/concerts";
    }

    @GetMapping("/concerts/{id}")
    public String showConcertDetails(@PathVariable Long id, Model model) {
        Concert concert = concertRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Concert not found with id: " + id));

        List<Rating> ratingList = ratingRepository.findAllRatingByConcertId(id);
        double averageRating = ratingService.getScoreAverage(id);
        model.addAttribute("concert", concert);
        model.addAttribute("ratings",ratingList);
        model.addAttribute("averageRating",averageRating);
        return "concert-details"; // Le nom du template Thymeleaf à afficher
    }

}
