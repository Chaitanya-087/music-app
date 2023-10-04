package com.music.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.music.api.entity.User;
import com.music.api.exception.NotFoundException;
import com.music.api.repository.UserRepository;

@Service
public class UserService {
    private static final String ENCODING_STRATEGY = "{bcrypt}";

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> authenticate(String username, String password) {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        if (!optUser.get().getPassword().equals(password)) {
            return Optional.empty();
        }
        return optUser;
    }

    private String prefixEncodingStrategyAndEncode(String rawString) {
        return ENCODING_STRATEGY + passwordEncoder.encode(rawString);
    }

    public User save(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(prefixEncodingStrategyAndEncode(password));
        return userRepository.save(user);
    }

    public User create(User user) {
        user.setPassword("{bcrypt}" + passwordEncoder.encode(user.getPassword()));
        // user.setPassword("{noop}" + user.getPassword());
        return userRepository.save(user);
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public Boolean existsByName(String name) {
        return userRepository.existsByUsername(name);
    }

    public Optional<User> getByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}
