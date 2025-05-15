package fr.formation.spring.concertnote.service;

import fr.formation.spring.concertnote.model.dto.LoginDto;
import fr.formation.spring.concertnote.model.dto.UserDto;
import fr.formation.spring.concertnote.model.entity.User;
import fr.formation.spring.concertnote.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
    }

    public User loginUser(LoginDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPasswordHash(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        User userInBase = userRepository.getUsersByEmail(user.getEmail());
        if (BCrypt.checkpw(dto.getPassword(), userInBase.getPasswordHash())) {
            return userInBase;
        }
        return null;
    }
}
