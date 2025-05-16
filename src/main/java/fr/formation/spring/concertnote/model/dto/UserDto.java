package fr.formation.spring.concertnote.model.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "A valid email is required")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Password required")
    @Size(min = 6, message = "The password must be at least 6 characters long.")
    private String password;

}