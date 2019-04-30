package com.infobip.web.urlshortener.services;

import com.infobip.web.urlshortener.domain.User;
import com.infobip.web.urlshortener.repositories.UserRepository;
import com.infobip.web.urlshortener.utilities.RandomStringUtility;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        User tmpUser = new User();
        if (userRepository.findUserByAccountId(user.getAccountId()) == null) {
            tmpUser.setAccountId(user.getAccountId());
            String password = RandomStringUtility.randomString(8);
            tmpUser.setPassword(password);
            tmpUser.setEncodedPassword(passwordEncoder.encode(password));
            tmpUser.setSuccess(true);
            tmpUser.setDescription("Your account is opened");
            tmpUser.setRole("ADMIN");
            userRepository.save(tmpUser);
            return tmpUser;
        } else {
            tmpUser.setDescription("User already exists!");
            tmpUser.setSuccess(false);
            return tmpUser;
        }
    }
}
