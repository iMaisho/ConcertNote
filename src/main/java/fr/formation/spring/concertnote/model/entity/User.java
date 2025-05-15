package fr.formation.spring.concertnote.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name= "password_hash", nullable = false)
    private String passwordHash;

    @OneToMany(mappedBy = "userexi", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    public void addRating(Rating rating) {
        this.ratings.add(rating);
        rating.setUser(this);
    }

    public void removeRating(Rating rating) {
        this.ratings.remove(rating);
        rating.setUser(null);
    }
}