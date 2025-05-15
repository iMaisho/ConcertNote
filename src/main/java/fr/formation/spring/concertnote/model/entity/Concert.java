
package fr.formation.spring.concertnote.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "concerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="concert_date", nullable = false)
    private LocalDate concertDate;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "bands_id", nullable = false)
    private Band band;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "venue_id", nullable = false)
    private Venue venue;

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    public void addRating(Rating rating) {
        this.ratings.add(rating);
        rating.setConcert(this);
    }

    public void removeRating(Rating rating) {
        this.ratings.remove(rating);
        rating.setConcert(null);
    }

}