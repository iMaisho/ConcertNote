
package fr.formation.spring.concertnote.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;


    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Concert> concerts = new ArrayList<>();

    public void addConcert(Concert concert) {
        this.concerts.add(concert);
        concert.setVenue(this);
    }

    public void removeConcert(Concert concert) {
        this.concerts.remove(concert);
        concert.setVenue(null);
    }
}