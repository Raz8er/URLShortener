package com.infobip.web.urlshortener.controllers;


import com.infobip.web.urlshortener.domain.dto.RegisteredUser;
import com.infobip.web.urlshortener.domain.dto.User;
import com.infobip.web.urlshortener.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(UserController.BASE_URL)
public class UserController {
    public static final String BASE_URL = "/api/v1/shortener/user";
    private final UserService userService;

    @PostMapping("/account")
    public RegisteredUser registerUser(@RequestBody @Valid User user) {
        return userService.registerUser(user);
    }
}
