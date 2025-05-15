package fr.formation.spring.concertnote.model.dto;


import jakarta.validation.constraints.*;

public class LoginDto {

    @NotBlank(message = "A valid email is required")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Password required")
    private String password;


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