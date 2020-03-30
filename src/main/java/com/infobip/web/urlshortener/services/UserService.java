package com.infobip.web.urlshortener.services;

import com.infobip.web.urlshortener.domain.dto.RegisteredUser;
import com.infobip.web.urlshortener.domain.dto.User;
import com.infobip.web.urlshortener.domain.entity.UserEntity;
import com.infobip.web.urlshortener.exception.UserAlreadyExistsException;
import com.infobip.web.urlshortener.mapping.UserMapper;
import com.infobip.web.urlshortener.repositories.UserRepository;
import com.infobip.web.urlshortener.utilities.RandomStringUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public RegisteredUser registerUser(@Valid User user) {
        if (Objects.nonNull(userRepository.findByAccountId(user.getAccountId()))) {
            throw new UserAlreadyExistsException("User already exists");
        }

        String password = RandomStringUtils.randomString(8);

        UserEntity userEntity = this.userMapper.toEntity(user);
        userEntity.setHashPassword(this.passwordEncoder.encode(password));
        userEntity.setRole("ADMIN");
        userEntity.setAccountId(user.getAccountId());
        this.userRepository.save(userEntity);

        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setSuccess(true);
        registeredUser.setDescription("Your account is opened");
        registeredUser.setPassword(password);
        return registeredUser;
    }
}
