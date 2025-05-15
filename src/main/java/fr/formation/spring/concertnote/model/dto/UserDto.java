package fr.formation.spring.concertnote.model.dto;


import jakarta.validation.constraints.*;

public class UserDto {

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "A valid email is required")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Password required")
    @Size(min = 6, message = "The password must be at least 6 characters long.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}