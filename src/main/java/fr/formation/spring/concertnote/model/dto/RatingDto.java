package fr.formation.spring.concertnote.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RatingDto {
    private Long concertId;
    private Long userId;
    private String description;

    @NotNull(message="Please provide a rating")
    private int score;
}
