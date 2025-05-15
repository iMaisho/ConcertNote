package fr.formation.spring.concertnote.controller;

import fr.formation.spring.concertnote.model.dto.LoginDto;
import fr.formation.spring.concertnote.model.entity.User;
import fr.formation.spring.concertnote.repository.UserRepository;
import fr.formation.spring.concertnote.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.spring.concertnote.model.dto.UserDto;

@Controller
@RequestMapping("/")
public class AuthController {


    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "layout";
    }

    // Affiche le formulaire vide
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    // Traite la soumission du formulaire
    @PostMapping("/register")
    public String processRegistration(
            @Valid
            @ModelAttribute("user") UserDto dto,
            BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "register";

        }
        userService.registerUser(dto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @Valid
            @ModelAttribute("user")LoginDto dto,
            BindingResult bindingResult,
            Model model,
            HttpSession session
            ){
        if (bindingResult.hasErrors()) {
            return "login";
        }
        User user = userService.loginUser(dto);
        session.setAttribute("userId", user.getId());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String processLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}


