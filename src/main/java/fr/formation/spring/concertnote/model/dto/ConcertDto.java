package fr.formation.spring.concertnote.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ConcertDto {

        @NotNull(message = "Date is required")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate concertDate;
        private String description;
        @NotNull(message  = "Please select a band")
        private Long band;
        @NotNull(message = "Please select a venue")
        private Long venue;

}

